package appllication.service;

import appllication.annotation.LogExecutionTime;
import appllication.entity.Auction;
import appllication.entity.Bidder;
import appllication.model.Exception.*;

import appllication.model.RequestPage;
import appllication.repository.BidderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import appllication.repository.AuctionDao;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

import java.time.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

@Component("auctionService")
public class AuctionService {


    private final BidderDao  bidderDao;

    private final AuctionDao auctionDao;

    @Autowired
    public AuctionService(@Qualifier("bidderDao") BidderDao bidderDao, @Qualifier("auctionDao") AuctionDao auctionDao) {
        this.bidderDao = bidderDao;
        this.auctionDao = auctionDao;
    }


    @LogExecutionTime
    @Transactional
    public Auction create(Auction anAuction)  {
          if(this.isMaxAuctionInProgress(anAuction.getEmailAuthor())){
              throw new MaxAuctionInProgressException("It is mostPopularAuction Auction in progress");
          }
          this.validation(anAuction);

           auctionDao.save(anAuction);
           return anAuction;
    }

    private void validation(Auction anAuction){
        if(this.isGreaterThanTheCurrentDay(LocalDateTime.now(), anAuction.getPublicationDate(), 1)){
            throw new ItIsNotGreaterThanTheCurrentDayException("It Is Not Greater Than The Current one Day");
        }

        if(this.isGreaterThanTheCurrentDay(anAuction.getPublicationDate(), anAuction.getFinishDate(), 2)){
            throw new ItIsNotGreaterThanTheCurrentDayException("It Is Not Greater Than The Current two Day");
        }
    }

    @LogExecutionTime
    @Transactional
    public Auction update(Auction anAuction){
        this.validation(anAuction);

        auctionDao.save(anAuction);
        return anAuction;
    }


    @LogExecutionTime
    @Transactional
    public Auction recover(String anAuthorName){
        Optional<Auction> anAuction = Optional.ofNullable(auctionDao.findByEmailAuthor(anAuthorName));

        if(! anAuction.isPresent()){
            throw new ThereIsNotAuctionException("There is not auction");
        }
        return anAuction.get();
    }

    @LogExecutionTime
    private boolean isMaxAuctionInProgress(String anEmailAuthor){
        List<Auction> someAuctionNewAndInProgress = auctionDao.findAllByEmailAuthor(anEmailAuthor).
                                                            stream().
                                                            filter(anAuction -> !anAuction.isFinished()).
                                                            collect(Collectors.toList());
        return someAuctionNewAndInProgress.size() >= 5 ;
    }

    private boolean isGreaterThanTheCurrentDay(LocalDateTime aLocalDateTime, LocalDateTime otherLocalDateTime, long aDay){

        return Duration.between(aLocalDateTime, otherLocalDateTime).abs().toDays() < aDay;
    }

    @LogExecutionTime
    @Transactional
    public Auction recoverById(long auctionId) {
        Optional<Auction> anAuction = auctionDao.findById(auctionId);
        if(! anAuction.isPresent()){
            throw new RuntimeException("No user was found");
        }
        return anAuction.get();
    }

    @LogExecutionTime
    @Transactional
    public Auction offer(long auctionId, String bidder) {
        Auction auction = this.recoverAndValidation(auctionId,bidder);
        auction.offer(bidder);
        auction.notifyAutomaticOffer();
        auctionDao.save(auction);
        return auction;
    }

    private Auction  recoverAndValidation(long auctionId,String bidder){
        Auction auction = recoverById(auctionId);
        auction.setFirstBidders(bidderDao.findAllByFirstBidderAndAuction(true,auctionId));

        if(auction.isAuthor(bidder)){
            throw new BidderIsTheAuctionAuthorException("Bidder is auction author");
        }
        if(auction.isTheLastBidder(bidder)){
            throw new LastBidderException("It is last bidder");
        }
        if(!auction.isInProgress()){
            throw new NotProgressException("Auction is not in progress");
        }
        return auction;
    }


    @LogExecutionTime
    @Transactional
    public Auction firstOffer(long auctionId, long maxAmount, String bidder) {
        Auction auction = this.recoverAndValidation(auctionId,bidder);
        if(auction.fivePercentMoreThanTheCurrentPrice() > maxAmount){
            throw new LastBidderException("Insufficient minimum price"); // change exception
        }
        auction.firstOffer(bidder, maxAmount);

        auctionDao.save(auction);
        return auction;
    }

    @LogExecutionTime
    @Transactional
    public Page<Auction> recoverAll(RequestPage aPage){
        return auctionDao.findAll(PageRequest.of(aPage.getIndex(),aPage.getSize()));}

    @LogExecutionTime
    @Transactional
    public  Page<Auction> recoverAllOrderByPublicationDate(RequestPage aPage){
        return auctionDao.findByPublicationDateGreaterThanOrderByPublicationDateDesc(PageRequest.of(aPage.getIndex(),aPage.getSize()),LocalDateTime.now());
    }

    @LogExecutionTime
    @Transactional

    public Page<Auction> recoverAllByTitleLikeAndDescriptionLike(RequestPage aPage){
        return auctionDao.findAllByTitleLikeAndDescriptionLike(aPage.getTitle(),
                                                        aPage.getDescription(),
                                                        PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @LogExecutionTime
    @Transactional
    public Page<Auction> recoverAllByTitleLike(RequestPage aPage){
        return auctionDao.findAllByTitleLike(aPage.getTitle(),PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }
    @LogExecutionTime
    @Transactional
    public Page<Auction> recoverAuctionsToFinish(RequestPage aPage){
        return auctionDao.findByFinishDateGreaterThanOrderByFinishDate(LocalDateTime.now(),PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }
    @LogExecutionTime
    @Transactional
    public Page<Auction> recoverAuctionsToFinishBetween(RequestPage aPage){
        return auctionDao.findAllByFinishDateBetween(aPage.getFirsTime(),aPage.getSecondTime(),PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @LogExecutionTime
    @Transactional
    public void delete(long id) {
        Optional<Auction> anAuctionOption = Optional.ofNullable(auctionDao.getOne(id));

        if(! anAuctionOption.isPresent()) {
            throw new HttpClientErrorException(BAD_REQUEST, "Not exist Auction with" + id);
        }

        Auction anyAuction = anAuctionOption.get();

        if(anyAuction.isInProgress()){
            throw new HttpClientErrorException(CONFLICT,"Auction is in progress");
        }

        if(anyAuction.isFinished()){
            throw new HttpClientErrorException(CONFLICT,"Auction is in isFinished");
        }

        auctionDao.deleteById(id);

    }

    @LogExecutionTime
    @Transactional
    public Auction popularAuction() {
       return auctionDao.mostPopularAuction(LocalDateTime.now());
    }

    @LogExecutionTime
    @Transactional
    public Page<Auction> findAllByTitleLikeAndDescriptionLikeAndEmailAuthorLike(RequestPage aPage) {
        return auctionDao.findAllByTitleLikeAndDescriptionLikeAndEmailAuthorLike(
                aPage.getTitle(),
                aPage.getDescription(),
                aPage.getUserName(),
                PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @LogExecutionTime
    @Transactional
    public Page<Auction> mostPopularAuctions(RequestPage aPage){
        return auctionDao.findAllByFinishDateGreaterThanOrderByCountBiddersDesc(
                LocalDateTime.now(),
                PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @LogExecutionTime
    @Transactional
    public Page<Auction> userParticipate(RequestPage aPage){
        return auctionDao.findAllByUserParticipate(
                aPage.getUserName(),
                PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @LogExecutionTime
    @Transactional
    public Page<Auction> usersParticipate(RequestPage aPage){
        return auctionDao.searchUsersParticipate(
            aPage.getUsersName().get(0),
            aPage.getUsersName().get(1),
            aPage.getUsersName().get(2),
            PageRequest.of( aPage.getIndex(),
                            aPage.getSize()));
    }

    @LogExecutionTime
    @Transactional
    public Auction recoverAuctionWithBidders(long id) {
        Auction auction = recoverById(id);
        auction.setFirstBidders(bidderDao.findAllByFirstBidderAndAuction(true,id));
        return auction;
    }
}

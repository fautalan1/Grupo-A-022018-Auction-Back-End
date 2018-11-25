package appllication.service;

import appllication.entity.Auction;
import appllication.model.Exception.*;

import appllication.model.RequestPage;
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

    private final AuctionDao auctionDao;

    @Autowired
    public AuctionService(@Qualifier("auctionDao") AuctionDao auctionDao) {
        this.auctionDao = auctionDao;
    }

    @Transactional
    public Auction create(Auction anAuction)  {
          if(this.isMaxAuctionInProgress(anAuction.getEmailAuthor())){
              throw new MaxAuctionInProgressException("It is max Auction in progress");
          }
          if(this.isGreaterThanTheCurrentDay(LocalDateTime.now(), anAuction.getPublicationDate(), 1)){
              throw new ItIsNotGreaterThanTheCurrentDayException("It Is Not Greater Than The Current one Day");
          }

          if(this.isGreaterThanTheCurrentDay(anAuction.getPublicationDate(), anAuction.getFinishDate(), 2)){
              throw new ItIsNotGreaterThanTheCurrentDayException("It Is Not Greater Than The Current two Day");
           }

           auctionDao.save(anAuction);
           return anAuction;
    }
    @Transactional
    public Auction update(Auction anAuction){
        auctionDao.save(anAuction);
        return anAuction;
    }

    @Transactional
    public Auction recover(String anAuthorName){
        Optional<Auction> anAuction = Optional.ofNullable(auctionDao.findByEmailAuthor(anAuthorName));

        if(! anAuction.isPresent()){
            throw new ThereIsNotAuctionException("There is not auction");
        }
        return anAuction.get();
    }


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

    @Transactional
    public Auction recoverById(long auctionId) {
        Optional<Auction> anAuction = auctionDao.findById(auctionId);
        if(! anAuction.isPresent()){
            throw new RuntimeException("No user was found");
        }
        return anAuction.get();
    }

    @Transactional
    public Auction offer(long auctionId, String bidder) {
        Auction auction = recoverById(auctionId);
        if(auction.isAuthor(bidder)){
            throw new BidderIsTheAuctionAuthorException("Bidder is auction author");
        }
        if(auction.isTheLastBidder(bidder)){
            throw new LastBidderException("It is last bidder");
        }
        if(!auction.isInProgress()){
            throw new NotProgressException("Auction is not in progress");
        }
        auction.offer(bidder);
        return update(auction);
    }

    @Transactional
    public Auction firstOffer(long auctionId, long maxAmount, String bidder) {
        Auction auction = recoverById(auctionId);
        if(auction.isAuthor(bidder)){
            throw new BidderIsTheAuctionAuthorException("Bidder is auction author");
        }
        if(!auction.getBidders().isEmpty()){
            throw new LastBidderException("Has already been offered"); // change exception
        }
        if(auction.fivePercentMoreThanTheCurrentPrice() > maxAmount){
            throw new LastBidderException("Insufficient minimum price"); // change exception
        }
        if(!auction.isInProgress()){
            throw new NotProgressException("Auction is not in progress");
        }
        auction.firstOffer(bidder, maxAmount);
        return update(auction);
    }

    @Transactional
    public Page<Auction> recoverAll(RequestPage aPage){
        return auctionDao.findAll(PageRequest.of(aPage.getIndex(),aPage.getSize()));}

    @Transactional
    public  Page<Auction> recoverAllOrderByPublicationDate(RequestPage aPage){
        return auctionDao.findByPublicationDateGreaterThanOrderByPublicationDateDesc(PageRequest.of(aPage.getIndex(),aPage.getSize()),LocalDateTime.now());
    }
    @Transactional

    public Page<Auction> recoverAllByTitleLikeAndDescriptionLike(RequestPage aPage){
        return auctionDao.findAllByTitleLikeAndDescriptionLike(aPage.getTitle(),
                                                        aPage.getDescription(),
                                                        PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }
    @Transactional
    public Page<Auction> recoverAllByTitleLike(RequestPage aPage){
        return auctionDao.findAllByTitleLike(aPage.getTitle(),PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @Transactional
    public Page<Auction> recoverAuctionsToFinish(RequestPage aPage){
        return auctionDao.findByFinishDateGreaterThanOrderByFinishDate(LocalDateTime.now(),PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

    @Transactional
    public Page<Auction> recoverAuctionsToFinishBetween(RequestPage aPage){
        return auctionDao.findAllByFinishDateBetween(aPage.getFirsTime(),aPage.getSecondTime(),PageRequest.of(aPage.getIndex(),aPage.getSize()));
    }

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


}

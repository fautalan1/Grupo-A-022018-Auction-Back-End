package appllication.service;

import appllication.entity.Auction;
import appllication.model.Exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @Transactional
    public List<Auction> recoverAll(){ return auctionDao.findAll();  }


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

    public List<Auction> recoverAllOrderByPublicationDate(){
        return auctionDao.findByPublicationDateGreaterThanOrderByPublicationDate(LocalDateTime.now());
    }
    @Transactional

    public List<Auction> recoverAllByTitleLikeAndDescriptionLike(String title, String description){
        return auctionDao.findAllByTitleLikeAndDescriptionLike(title,description);
    }
    @Transactional
    public List<Auction> recoverAllByTitleLike(String title){
        return auctionDao.findAllByTitleLike(title);
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

    @Transactional
    public List<Auction> recoverAuctionsToFinish(){
        return auctionDao.findByFinishDateLessThanOrderByFinishDate(LocalDateTime.now());
    }

    @Transactional
    public List<Auction> recoverAuctionsToFinishBetween(LocalDateTime aDateTime , LocalDateTime otherDateTime){
        return auctionDao.findAllByFinishDateBetween(aDateTime,otherDateTime);
    }
}

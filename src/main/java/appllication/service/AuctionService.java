package appllication.service;

import appllication.entity.Auction;
import appllication.model.Exception.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import appllication.repository.AuctionDao;
import org.springframework.transaction.annotation.Transactional;

import java.time.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component("auctionService")
public class AuctionService {

    @Autowired
    @Qualifier("auctionDao")
    private AuctionDao auctionDao;

    @Transactional
    public Auction create(Auction anAuction)  {
          if(this.isMaxAuctionInProgress(anAuction.getEmailAuthor())){
              throw new MaxAuctionInProgressException("It is max Auction in progress");
          }

          if(!this.isGreaterThanTheCurrentDay(LocalDateTime.now(),anAuction.getPublicationDate(),1)){
              throw new ItIsNotGreaterThanTheCurrentDayException("It Is Not Greater Than The Current  one Day");
          }

          if(!this.isGreaterThanTheCurrentDay(anAuction.getPublicationDate(),anAuction.getFinishDate(),2)){
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
        List<Auction> someAuctionNewAndInProgress= auctionDao.findAllByEmailAuthor(anEmailAuthor).
                                                            stream().
                                                            filter(anAuction -> !anAuction.isFinished()).
                                                            collect(Collectors.toList());
        return someAuctionNewAndInProgress.size() >= 5 ;
    }

    private boolean isGreaterThanTheCurrentDay(LocalDateTime aLocalDateTime,LocalDateTime otherLocalDateTime,long aDay){

        return Duration.between(aLocalDateTime, otherLocalDateTime).abs().toDays() >= aDay;
    }

    public Auction recoverById(long auctionId) {
        Optional<Auction> anAuction = auctionDao.findById(auctionId);
        if(! anAuction.isPresent()){
            throw new RuntimeException("No user was found");
        }
        return anAuction.get();
    }


}

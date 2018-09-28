package appllication.service;

import appllication.entity.Auction;
import appllication.model.Exception.MaxAuctionInProgressException;
import appllication.model.Exception.ThereIsNotAuctionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import appllication.repository.AuctionDao;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
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

          if( this.isGreaterThanTheCurrentDay(anAuction.getPublicationDate())){
              throw new RuntimeException("Fire");
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
    public List<Auction> recoverAll(){   return auctionDao.findAll();   }


    private boolean isMaxAuctionInProgress(String anEmailAuthor){
        List<Auction> someAuctionNewAndInProgress= auctionDao.findAllByEmailAuthor(anEmailAuthor).
                                                            stream().
                                                            filter(anAuction -> !anAuction.isFinished()).
                                                            collect(Collectors.toList());

        return someAuctionNewAndInProgress.size() >= 5 ;
    }
    private boolean isGreaterThanTheCurrentDay(Date aPublicationDate){
        Date now = Calendar.getInstance().getTime();
        return aPublicationDate.compareTo(now) < 0;
    }







}

package appllication.service;

import appllication.entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import appllication.repository.AuctionDao;

import java.util.List;
import java.util.Optional;

@Component("auctionService")
public class AuctionService {


    @Autowired//para indicar que inyecte spring
    @Qualifier("auctionDao")//para decirle que va estar inyectando
    private AuctionDao auctionDao;

    public Auction newA(Auction anAuction){
           auctionDao.save(anAuction);
           return anAuction;
    }
    public Auction update(Auction anAuction){
        auctionDao.save(anAuction);
        return anAuction;

    }


    public Auction recover(String anAuthorName){
        Optional<Auction> anAuction = Optional.ofNullable(auctionDao.findByAuthor(anAuthorName));

        if(! anAuction.isPresent()){
            throw new RuntimeException("No existe");
        }
        return anAuction.get();
    }



    public List<Auction> recoverAll(){

        return auctionDao.findAll();
    }





}

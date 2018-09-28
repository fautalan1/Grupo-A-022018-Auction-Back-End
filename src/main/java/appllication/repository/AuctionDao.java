package appllication.repository;

import appllication.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Component("auctionDao")
public interface AuctionDao extends JpaRepository<Auction, Long> {


    public abstract Auction findByAuthor(String anAuthorName);


}

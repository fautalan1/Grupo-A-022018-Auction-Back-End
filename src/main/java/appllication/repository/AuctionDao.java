package appllication.repository;

import appllication.entity.Auction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;


@Component("auctionDao")
public interface AuctionDao extends JpaRepository<Auction, Long> {


    Auction findByEmailAuthor(String anEmailAuthor);
    List<Auction>  findAllByEmailAuthor(String anEmailAuthor);
}

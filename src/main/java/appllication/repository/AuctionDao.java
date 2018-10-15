package appllication.repository;

import appllication.entity.Auction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@Component("auctionDao")
public interface AuctionDao extends JpaRepository<Auction, Long>{


    //Optional<Auction> findAllByEmailAuthor(String an);

    Auction        findByEmailAuthor(String anEmailAuthor);
    List<Auction>  findAllByEmailAuthor(String anEmailAuthor);

    List<Auction>  findByOrderByPublicationDateAsc(Pageable pageable);

}

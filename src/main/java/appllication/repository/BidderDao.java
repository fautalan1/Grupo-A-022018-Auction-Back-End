package appllication.repository;

import appllication.entity.Bidder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("bidderDao")
public interface BidderDao extends JpaRepository<Bidder, Long> {


    @Query(value = "select b from Bidder b where b.auction.id = :id and b.firstBidder = :isFirstBidder ")
    List<Bidder> findAllByFirstBidderAndAuction(@Param("isFirstBidder") boolean isFirstBidder,@Param("id") long id);
}

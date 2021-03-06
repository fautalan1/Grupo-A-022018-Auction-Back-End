package appllication.repository;

import appllication.entity.Auction;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;



@Component("auctionDao")
public interface AuctionDao extends JpaRepository<Auction, Long>{


    Auction        findByEmailAuthor(String anEmailAuthor);


    List<Auction>  findAllByEmailAuthor(String anEmailAuthor);

    Page<Auction> findByFinishDateGreaterThanOrderByFinishDate(LocalDateTime finishDate,Pageable pageable);

    //Page<Auction> findByBiddersIsContaining(String aUserName,Pageable pageable)

    Page<Auction> findByPublicationDateGreaterThanOrderByPublicationDateDesc(Pageable pageable, LocalDateTime publicationDate);

    @Query(value = "select a from Auction a where a.title like concat('%', :titleToFind,'%') and a.description like (concat('%',:description,'%'))")
    Page<Auction> findAllByTitleLikeAndDescriptionLike(@Param("titleToFind") String title, @Param("description") String description,Pageable pageable);

    @Query(value = "select a from Auction a where a.title like concat('%', :titleToFind,'%')")
    Page<Auction> findAllByTitleLike(@Param("titleToFind") String title,Pageable pageable);

    @Query(value = "select a from Auction a where a.title like concat('%', :titleToFind,'%') and a.description like (concat('%',:description,'%')) and a.emailAuthor like (concat('%',:anEmailAuthor,'%'))")
    Page<Auction> findAllByTitleLikeAndDescriptionLikeAndEmailAuthorLike(@Param("titleToFind") String title, @Param("description") String description,@Param("anEmailAuthor") String anEmailAuthor ,Pageable pageable);

    Page<Auction> findAllByFinishDateBetween(LocalDateTime aDateTime, LocalDateTime otherDateTime,Pageable pageable);

    @Query(value = "SELECT a FROM Auction a WHERE a.countBidders = (SELECT MAX(countBidders) FROM Auction b where b.finishDate > :now)")
    Auction  mostPopularAuction(@Param("now") LocalDateTime now);

    @Query(value = " SELECT auc FROM Auction auc where auc.id IN (SELECT b.auction.id FROM Bidder b where b.author = :userName)")
    Page<Auction> findAllByUserParticipate(@Param("userName") String userName, Pageable pageable);

    @Query(value = " SELECT auc FROM Auction auc where auc.emailAuthor IN (SELECT b.author FROM Bidder b where b.author = :oneUserName or b.author = :twoUserName or b.author = :threeUserName  )")
    Page<Auction> searchUsersParticipate(@Param("oneUserName")   String oneUserName,
                                         @Param("twoUserName")   String twoUserName,
                                         @Param("threeUserName") String threeUserName, Pageable pageable);



    Page<Auction> findAllByFinishDateGreaterThanOrderByCountBiddersDesc(LocalDateTime anyDateTime,Pageable pageable);
}

package appllication.repository;

import appllication.entity.Auction;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component("auctionDao")
public interface AuctionDao extends JpaRepository<Auction, Long>{

    Auction        findByEmailAuthor(String anEmailAuthor);
    List<Auction>  findAllByEmailAuthor(String anEmailAuthor);

    List<Auction> findByFinishDateLessThanOrderByFinishDate(LocalDateTime finishDate,Pageable pageable);


    List<Auction>  findByPublicationDateGreaterThanOrderByPublicationDateDesc(Pageable pageable,LocalDateTime publicationDate);

    @Query(value = "select a from Auction a where a.title like concat('%', :titleToFind,'%') and a.description like (concat('%',:description,'%'))")
    List<Auction> findAllByTitleLikeAndDescriptionLike(@Param("titleToFind") String title, @Param("description") String description,Pageable pageable);

    @Query(value = "select a from Auction a where a.title like concat('%', :titleToFind,'%')")
    List<Auction> findAllByTitleLike(@Param("titleToFind") String title,Pageable pageable);


    List<Auction> findAllByFinishDateBetween(LocalDateTime aDateTime, LocalDateTime otherDateTime,Pageable pageable);


}

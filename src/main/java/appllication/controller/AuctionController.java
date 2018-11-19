package appllication.controller;

import appllication.annotation.LogExecutionTime;
import appllication.entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import appllication.service.AuctionService;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
public class AuctionController {

    private final AuctionService auctionService;

    @Autowired
    public AuctionController(@Qualifier("auctionService") AuctionService auctionService) {
        this.auctionService = auctionService;
    }

    /* Get
     *
     *
     *
     * */

    @LogExecutionTime
    @GetMapping("/auctions/{index}/{size}")
    public Page<Auction> all(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAll(index,size);
    }

    @LogExecutionTime
    @GetMapping("/auction/recover/{id}")
    public Auction recover(@PathVariable("id") long id ){
        return auctionService.recoverById(id);
    }

    @LogExecutionTime
    @GetMapping("/auction/{emailAuthor}")
    public Auction recover(@PathVariable("emailAuthor") String emailAuthor ){
        return auctionService.recover(emailAuthor);
    }

    @LogExecutionTime
    @GetMapping("/auctions/recentAuctions/{index}/{size}")
    public  Page<Auction> recentAuctions(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAllOrderByPublicationDate(index,size);
    }

    @LogExecutionTime
    @GetMapping("/auction/{title}/{description}/{index}/{size}")
    public Page<Auction> allBy(@PathVariable("title") String title, @PathVariable String description,
                               @PathVariable int index, @PathVariable int size){
        return auctionService.recoverAllByTitleLikeAndDescriptionLike(title,description,index,size);
    }

    @LogExecutionTime
    @GetMapping("/auction/for/{title}/{index}/{size}")
    public Page<Auction> allBy(@PathVariable("title") String title, @PathVariable int index, @PathVariable int size){
        return auctionService.recoverAllByTitleLike(title,index,size);
    }

    @LogExecutionTime
    @GetMapping("/auction/toFinish/{index}/{size}")
    public Page<Auction> toFinish(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAuctionsToFinish(index,size);
    }

    @LogExecutionTime
    @GetMapping("/auction/toFinishBetween/{index}/{size}")
    public Page<Auction> toFinishBetween(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAuctionsToFinishBetween(LocalDateTime.now(),LocalDateTime.now().plusDays(1),index,size);
    }

    /**********************************************************************************/


    /* Put
     *
     *
     *
     * */
    @LogExecutionTime
    @PutMapping("/auction")
    public Auction add(@RequestBody @Valid Auction anAuction){
        return auctionService.create(anAuction);
    }

    /**********************************************************************************/

    /* Post
     *
     *
     *
     * */
    @LogExecutionTime
    @PostMapping("/auction")
    public Auction update(@RequestBody @Valid Auction anAuction){
        return auctionService.update(anAuction);
    }

    @LogExecutionTime
    @PostMapping("/auction/{auctionId}/offer/{bidder}")
    public Auction offer(@PathVariable("auctionId") long auctionId, @PathVariable("bidder") String bidder){
        return auctionService.offer(auctionId, bidder);
    }

    @PostMapping("/auction/first/offer/{auctionId}/{maxAmount}/{bidder}")
    public Auction firstOffer(@PathVariable("auctionId") long auctionId,
                              @PathVariable("maxAmount") long maxAmount,
                              @PathVariable("bidder") String bidder){
        return auctionService.firstOffer(auctionId, maxAmount, bidder);
    }

    /**********************************************************************************/

    /* Delete
     *
     *
     *
     * */
    @LogExecutionTime
    @DeleteMapping("/auction/delete/{id}")
    public void delete(@PathVariable long id){
        auctionService.delete(id);
    }



}
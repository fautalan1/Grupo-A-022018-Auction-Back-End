package appllication.controller;

import appllication.annotation.LogExecutionTime;
import appllication.entity.Auction;
import appllication.model.RequestPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
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
    @GetMapping("/auction/recover/{id}")
    public Auction recover(@PathVariable("id") long id ){
        return auctionService.recoverById(id);
    }

    @LogExecutionTime
    @GetMapping("/auction/{emailAuthor}")
    public Auction recover(@PathVariable("emailAuthor") String emailAuthor ){
        return auctionService.recover(emailAuthor);
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
    @PostMapping("/auctions")
    public Page<Auction> all(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAll(aPage);
    }


    @LogExecutionTime
    @PostMapping("/auctions/recentAuctions")
    public  Page<Auction> recentAuctions(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAllOrderByPublicationDate(aPage);
    }

    @LogExecutionTime
    @PostMapping("/auction/title_and_description")
    public Page<Auction> allByTitleAndDescription(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAllByTitleLikeAndDescriptionLike(aPage);
    }

    @LogExecutionTime
    @PostMapping("/auction/title")
    public Page<Auction> allByTitle(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAllByTitleLike(aPage);
    }

    @LogExecutionTime
    @PostMapping("/auction/toFinish")
    public Page<Auction> allToFinish(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAuctionsToFinish(aPage);
    }

    @LogExecutionTime
    @PostMapping("/auction/toFinishBetween")
    public Page<Auction> allToFinishBetween(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAuctionsToFinishBetween(aPage);
    }


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

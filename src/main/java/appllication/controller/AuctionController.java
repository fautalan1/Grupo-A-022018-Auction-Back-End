package appllication.controller;

import appllication.entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

    @GetMapping("/auctions/{index}/{size}")
    public List<Auction> all(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAll(index,size);
    }

    @GetMapping("/auction/{emailAuthor}")
    public Auction recover(@PathVariable("emailAuthor") String emailAuthor ){
        return auctionService.recover(emailAuthor);
    }

    @GetMapping("/auctions/recentAuctions/{index}/{size}")
    public List<Auction> recentAuctions(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAllOrderByPublicationDate(index,size);
    }


    @GetMapping("/auction/{title}/{description}/{index}/{size}")
    public List<Auction> allBy(@PathVariable("title") String title, @PathVariable String description,
                               @PathVariable int index, @PathVariable int size){
        return auctionService.recoverAllByTitleLikeAndDescriptionLike(title,description,index,size);
    }

    @GetMapping("/auction/for/{title}/{index}/{size}")
    public List<Auction> allBy(@PathVariable("title") String title, @PathVariable int index, @PathVariable int size){
        return auctionService.recoverAllByTitleLike(title,index,size);
    }


    @GetMapping("/auction/toFinish/{index}/{size}")
    public List<Auction> toFinish(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAuctionsToFinish(index,size);
    }

    @GetMapping("/auction/toFinishBetween/{index}/{size}")
    public List<Auction> toFinishBetween(@PathVariable int index, @PathVariable int size){
        return auctionService.recoverAuctionsToFinishBetween(LocalDateTime.now(),LocalDateTime.now().plusDays(1),index,size);
    }

    /**********************************************************************************/


    /* Put
     *
     *
     *
     * */

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

    @PostMapping("/auction")
    public Auction update(@RequestBody @Valid Auction anAuction){
        return auctionService.update(anAuction);
    }

    @PostMapping("/auction/{auctionId}/offer/{bidder}")
    public Auction offer(@PathVariable("auctionId") long auctionId, @PathVariable("bidder") String bidder){
        return auctionService.offer(auctionId, bidder);
    }

    /**********************************************************************************/

    /* Delete
     *
     *
     *
     * */
    @DeleteMapping("/auction/delete/{id}")
    public void delete(@PathVariable long id){
        auctionService.delete(id);
    }



}

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

    @GetMapping("/auctions")
    public List<Auction> all(){
        return auctionService.recoverAll();
    }

    @GetMapping("/auction/{emailAuthor}")
    public Auction recover(@PathVariable("emailAuthor") String emailAuthor ){
        return auctionService.recover(emailAuthor);
    }

    @GetMapping("/auctions/recentAuctions")
    public List<Auction> recentAuctions(){
        return auctionService.recoverAllOrderByPublicationDate();
    }


    @GetMapping("/auction/{title}/{description}")
    public List<Auction> allBy(@PathVariable("title") String title, @PathVariable String description){
        return auctionService.recoverAllByTitleLikeAndDescriptionLike(title,description);
    }

    @GetMapping("/auction/for/{title}")
    public List<Auction> allBy(@PathVariable("title") String title){
        return auctionService.recoverAllByTitleLike(title);
    }


    @GetMapping("/auction/toFinish")
    public List<Auction> toFinish(){
        return auctionService.recoverAuctionsToFinish();
    }

    @GetMapping("/auction/toFinishBetween")
    public List<Auction> toFinishBetween(){
        return auctionService.recoverAuctionsToFinishBetween(LocalDateTime.now(),LocalDateTime.now().plusDays(1));
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

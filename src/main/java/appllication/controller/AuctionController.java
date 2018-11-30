package appllication.controller;

import appllication.entity.Auction;
import appllication.model.RequestPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import appllication.service.AuctionService;
import javax.validation.Valid;

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
    @GetMapping("/auction/popular")
    public Auction recover(){return auctionService.popularAuction();}



    @GetMapping("/auction/recover/{id}")
    public Auction recover(@PathVariable("id") long id ){
        return auctionService.recoverById(id);
    }
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
    @PutMapping("/new/auction")
    public Auction add(@RequestBody @Valid Auction anAuction){
        return auctionService.create(anAuction);
    }

    /**********************************************************************************/

    /* Post
     *
     *
     *
     * */
    @PostMapping("/auctions")
    public Page<Auction> all(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAll(aPage);
    }



    @PostMapping("/auctions/title_and_description_and_user_name")
    public  Page<Auction> allByTitleAndDescriptionAndUserName(@RequestBody @Valid RequestPage aPage){
        return auctionService.findAllByTitleLikeAndDescriptionLikeAndEmailAuthorLike(aPage);
    }

    @PostMapping("/auctions/recentAuctions")
    public  Page<Auction> recentAuctions(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAllOrderByPublicationDate(aPage);
    }


    @PostMapping("/auction/title_and_description")
    public Page<Auction> allByTitleAndDescription(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAllByTitleLikeAndDescriptionLike(aPage);
    }


    @PostMapping("/auction/title")
    public Page<Auction> allByTitle(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAllByTitleLike(aPage);
    }


    @PostMapping("/auction/toFinish")
    public Page<Auction> allToFinish(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAuctionsToFinish(aPage);
    }


    @PostMapping("/auction/toFinishBetween")
    public Page<Auction> allToFinishBetween(@RequestBody @Valid RequestPage aPage){
        return auctionService.recoverAuctionsToFinishBetween(aPage);
    }

    @PostMapping("/auction/update")
    public Auction update(@RequestBody @Valid Auction anAuction){
        return auctionService.update(anAuction);
    }


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

    @PostMapping("/auctions/popular")
    public Page<Auction>  recoverMostPopularAuction(@RequestBody @Valid RequestPage aPage){
        return auctionService.mostPopularAuctions(aPage);
    }

    @PostMapping("/auctions/user/participate")
    public Page<Auction> recoverAuctionsUserParticipate(@RequestBody @Valid RequestPage aPage){
        return auctionService.userParticipate(aPage);
    }

     @PostMapping("/auctions/users/participate")
     public Page<Auction> recoverAuctionsUsersParticipate(@RequestBody @Valid RequestPage aPage){
        return auctionService.usersParticipate(aPage);
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

package appllication.controller;

import appllication.entity.Auction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import appllication.service.AuctionService;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AuctionController {

    @Autowired//para indicar que inyecte spring
    @Qualifier("auctionService")//para decirle que va estar inyectando
    private AuctionService auctionService;

    @GetMapping("/auctions")
    public List<Auction> all(){
        return auctionService.recoverAll();
    }

    @GetMapping("/auction/{name}")
    public Auction recover(@PathVariable("name") String name){
        return auctionService.recover(name);
    }

    @PutMapping("/auction")
    public Auction add(@RequestBody @Valid Auction anAuction){
        return auctionService.newA(anAuction);
    }

    @PostMapping("/auction")
    public Auction update(@RequestBody @Valid Auction anAuction){
        return auctionService.update(anAuction);
    }

    @PostMapping("/auction/{auctionId}/offer/{bidder}")
    public Auction offer(@PathVariable("auctionId") long auctionId, @PathVariable("bidder") String bidder){
        // Validations
        Auction auction = auctionService.recoverById(auctionId);
        auction.offer(bidder);
        return auctionService.update(auction);
    }

}

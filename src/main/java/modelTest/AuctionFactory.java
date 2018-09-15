package modelTest;

import entity.Auction;

import java.util.Date;

class AuctionFactory {
    
    static Auction anyAuction() {
        return new Auction(new Date(), new Date());
    }


}
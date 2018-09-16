package model;

import entity.Auction;

import java.util.Date;

public class AuctionFactory {

    public static Auction anyAuction() {
        return new Auction(new Date(), new Date());
    }

}
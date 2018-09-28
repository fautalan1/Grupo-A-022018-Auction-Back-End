package appllication.model.factory;

import appllication.entity.Auction;


import java.util.Date;


public class AuctionFactory {

    public static Auction anyAuction() {
        Auction anAuction = new Auction();
        anAuction.setPublicationDate(new DateFactory().now());
        anAuction.setFinishDate(new DateFactory().now());
        anAuction.setInitialFinishDate(new DateFactory().now());
        anAuction.setAutomaticOfferAmount(0);
        return anAuction;
    }



}
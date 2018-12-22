package appllication.model.factory;

import appllication.entity.Auction;


import java.time.LocalDateTime;
import java.util.Date;


public class AuctionFactory {

    public static Auction anyAuction() {
        Auction anAuction = new Auction();
        anAuction.setPublicationDate(LocalDateTime.now());
        anAuction.setFinishDate(LocalDateTime.now());
        anAuction.setInitialFinishDate(LocalDateTime.now());
        anAuction.setAutomaticOfferAmount(0);
        return anAuction;
    }



}
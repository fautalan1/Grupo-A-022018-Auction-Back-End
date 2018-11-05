package appllication.model.factory;

import appllication.entity.Auction;

import java.time.LocalDateTime;

public class BuilderAuction {
    private Auction anyAction;


    public BuilderAuction anyAuction() {
        Auction anAuction = new Auction();
        anAuction.setPublicationDate(LocalDateTime.now());
        anAuction.setFinishDate(LocalDateTime.now());
        anAuction.setInitialFinishDate(LocalDateTime.now());
        anAuction.setAutomaticOfferAmount(0);
        anyAction = anAuction;
        return this;
    }

    public BuilderAuction whitTitle(String title){
        anyAction.setTitle(title);
        return this;
    }

    public BuilderAuction whitDescription(String description){
        anyAction.setDescription(description);
        return this;
    }
    public BuilderAuction whitEmailAuthor(String emailAuthor){
        anyAction.setEmailAuthor(emailAuthor);
        return this;
    }

    public Auction get(){
        return anyAction;
    }



}

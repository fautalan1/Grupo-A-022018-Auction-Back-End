package appllication.model.factory;

import appllication.entity.Auction;

import java.time.LocalDateTime;

public class BuilderAuction {
    private Auction anyAction;


    public BuilderAuction anyAuction() {
        Auction anAuction = new Auction();
        anAuction.setPublicationDate(LocalDateTime.now().plusDays(2));
        anAuction.setFinishDate(LocalDateTime.now().plusDays(10));
        anAuction.setInitialFinishDate(LocalDateTime.now().plusDays(10));
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

    public BuilderAuction whitPublicationDate(LocalDateTime aTime){
        anyAction.setPublicationDate(aTime);
        return this;
    }

    public BuilderAuction whitFinishDate(LocalDateTime aTime){
        anyAction.setFinishDate(aTime);
        return this;
    }

    public BuilderAuction whitInitialFinishDate(LocalDateTime aTime){
        anyAction.setInitialFinishDate(aTime);
        return this;
    }
    public BuilderAuction whitAutomaticOfferAmount(long aAmount){
        anyAction.setAutomaticOfferAmount(aAmount);
        return this;
    }


    public Auction get(){
        return anyAction;
    }



}

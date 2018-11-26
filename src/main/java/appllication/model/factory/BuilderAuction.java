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

    public BuilderAuction withTitle(String title){
        anyAction.setTitle(title);
        return this;
    }

    public BuilderAuction withDescription(String description){
        anyAction.setDescription(description);
        return this;
    }
    public BuilderAuction withEmailAuthor(String emailAuthor){
        anyAction.setEmailAuthor(emailAuthor);
        return this;
    }

    public BuilderAuction withPublicationDate(LocalDateTime aTime){
        anyAction.setPublicationDate(aTime);
        return this;
    }

    public BuilderAuction withFinishDate(LocalDateTime aTime){
        anyAction.setFinishDate(aTime);
        return this;
    }

    public BuilderAuction withInitialFinishDate(LocalDateTime aTime){
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


    public BuilderAuction withPrice(long amount) {
        anyAction.setPrice(amount);
        return this;
    }

    public BuilderAuction withPhoto(String photo) {
        anyAction.setPhotos(photo);
        return this;
    }

    public BuilderAuction withBidder(String aBidder){
        anyAction.offer(aBidder);
        return this;
    }
}

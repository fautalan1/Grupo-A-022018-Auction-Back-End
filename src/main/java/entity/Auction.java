package entity;

import modelTest.AuctionStatus;
import modelTest.DateFactory;
import modelTest.StatusProviderOfAnAuction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Auction {

    private long id;
    private Date publicationDate;
    private Date finishDate;
    private long price;
    private List<Offer> offers;
    
    public Auction(Date aPublicationDate, Date aFinishDate) {
        super();
        setPublicationDate(aPublicationDate);
        setFinishDate(aFinishDate);
        setOffers(new ArrayList<>());
    }

    public void setPublicationDate(Date aDate) {
        this.publicationDate = aDate;
    }

    public void setFinishDate(Date aDate) {
        this.finishDate = aDate;
    }

    public AuctionStatus state() {
        StatusProviderOfAnAuction statusProvider = new StatusProviderOfAnAuction();
        return statusProvider.getState(this);
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    private void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    private void addOffer(Offer offer) {
        this.offers.add(offer);
    }

    public void offer(String email) {
        Offer newOffer = new Offer(email, this.id);
        addOffer(newOffer);
        long newPrice = fivePercentMoreThanTheCurrentPrice();
        setPrice(newPrice);
        if(theAuctionMustBeExtended()) {
            setFinishDate(DateFactory.addFiveMinutes(this.finishDate));
        }
    }

    private boolean theAuctionMustBeExtended() {
        Date fiveMinutesBeforeTheCurrent = DateFactory.fiveMinutesBeforeTheCurrent();
        return fiveMinutesBeforeTheCurrent.compareTo(this.finishDate) < 0;
    }

    private long fivePercentMoreThanTheCurrentPrice() {
        return (this.price * 5 / 100) + this.price;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
package entity;

import model.AuctionStatus;
import model.DateFactory;
import model.StatusProviderOfAnAuction;
import java.util.Date;

public class Auction {

    private Date publicationDate;
    private Date finishDate;
    private Date initialFinishDate;
    private long price;
    private String automaticOfferUser;
    private long automaticOfferAmount;

    public Auction(Date aPublicationDate, Date aFinishDate) {
        super();
        setPublicationDate(aPublicationDate);
        setFinishDate(aFinishDate);
        setInitialFinishDate(aFinishDate);
        setAutomaticOfferAmount(0);
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

    private boolean theAuctionMustBeExtended() {
        return theOfferWasMadeWithinTheLastFiveMinutes() && !exceededFortyEightHours();
    }

    private boolean exceededFortyEightHours() {
        Date fiveMinutesAfterTheFinishDate = DateFactory.addFiveMinutes(this.finishDate);
        Date initialFinishDatePlusFortyEightHours = DateFactory.addFortyEightHours(this.initialFinishDate);
        return fiveMinutesAfterTheFinishDate.compareTo(initialFinishDatePlusFortyEightHours) > 0;
    }

    private boolean theOfferWasMadeWithinTheLastFiveMinutes() {
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

    public void offer() {
        long newPrice = fivePercentMoreThanTheCurrentPrice();
        setPrice(newPrice);
        if(theAuctionMustBeExtended()) {
            setFinishDate(DateFactory.addFiveMinutes(this.finishDate));
        }
    }

    public void automaticOffer(String email, long amount) {
        setAutomaticOfferUser(email);
        setAutomaticOfferAmount(amount);
    }

    public boolean thereIsToDoTheAutomaticOffer() {
        long newPrice = fivePercentMoreThanTheCurrentPrice();
        return this.automaticOfferAmount != 0 && newPrice <= this.automaticOfferAmount;
    }

    public void setInitialFinishDate(Date initialFinishDate) {
        this.initialFinishDate = initialFinishDate;
    }

    private void setAutomaticOfferAmount(long automaticOfferAmount) {
        this.automaticOfferAmount = automaticOfferAmount;
    }

    private void setAutomaticOfferUser(String automaticOfferUser) {
        this.automaticOfferUser = automaticOfferUser;
    }
}
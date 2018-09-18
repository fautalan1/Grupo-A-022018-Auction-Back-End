package entity;

import model.AuctionStatus;
import model.DateFactory;
import model.StatusProviderOfAnAuction;
import org.springframework.util.StringUtils;

import java.util.Date;

public class Auction {

    private String author;
    private Date publicationDate;
    private Date finishDate;
    private Date initialFinishDate;
    private long price;
    private String automaticOfferUser;
    private long automaticOfferAmount;

    final private StatusProviderOfAnAuction statusProvide= new StatusProviderOfAnAuction();

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

    final public AuctionStatus state() {

        return statusProvide.getState(this);
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public void setAutomaticOfferAmount(long automaticOfferAmount) {
        this.automaticOfferAmount = automaticOfferAmount;
    }



    public void setAutomaticOfferUser(String automaticOfferUser) {
        this.automaticOfferUser = automaticOfferUser;
    }

    public boolean thereIsAutomaticUser() {
      return !StringUtils.isEmpty(automaticOfferUser);
    }

    public boolean isFinished(){

        return this.state().equals(AuctionStatus.COMPLETED);
    }

    public boolean  isAuthor(String aBidder){
      return  aBidder.equalsIgnoreCase(author);

    }
    public boolean isInProgress(){
        return this.state().equals(AuctionStatus.IN_PROGRESS);
    }



}
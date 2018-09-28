package entity;

import model.AuctionStatus;
import model.DateFactory;
import model.StatusProviderOfAnAuction;
import org.springframework.util.StringUtils;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
public class Auction {


    @GeneratedValue
    @Id
    private long id;

    private String author;
    private Date publicationDate;
    private Date finishDate;
    private Date initialFinishDate;
    private long price;
    private String automaticOfferUser;
    private long automaticOfferAmount;

    @OneToMany(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Bidders> bidders;

    public Auction(){}

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
        StatusProviderOfAnAuction statusProvide= new StatusProviderOfAnAuction();
        return statusProvide.getState(this);
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public Date getFinishDate() {
        return finishDate;
    }


    public void setAuthor(String author) {
        this.author = author;
    }

    private boolean theAuctionMustBeExtended() {
        return theOfferWasMadeWithinTheLastFiveMinutes() && !exceededFortyEightHours();
    }

    private boolean exceededFortyEightHours() {
        Date fiveMinutesAfterTheFinishDate =DateFactory.add(this.finishDate, Calendar.MINUTE,5);
        Date initialFinishDatePlusFortyEightHours = DateFactory.add(this.initialFinishDate, Calendar.DAY_OF_WEEK,2);
        return fiveMinutesAfterTheFinishDate.compareTo(initialFinishDatePlusFortyEightHours) > 0;
    }

    private boolean theOfferWasMadeWithinTheLastFiveMinutes() {
        Date fiveMinutesBeforeTheCurrent =DateFactory.add(new Date(), Calendar.MINUTE,-1);

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
            setFinishDate(DateFactory.add(this.finishDate, Calendar.MINUTE,5));
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
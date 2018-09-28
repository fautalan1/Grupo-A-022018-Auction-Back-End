package appllication.entity;

import appllication.model.AuctionStatus;
import appllication.model.DateFactory;
import appllication.model.StatusProviderOfAnAuction;
import org.springframework.util.StringUtils;

import javax.persistence.*;


import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Entity
public class Auction  {


    @GeneratedValue
    @Id
    private long id;


    private String author;
    private String automaticOfferUser;

    private Date publicationDate;
    private Date finishDate;
    private Date initialFinishDate;

    private long price;
    private long automaticOfferAmount;

    @OneToMany(fetch= FetchType.EAGER,cascade=CascadeType.ALL)
    private List<Bidders> bidders;

    public Auction(){}

    public Auction(Date aPublicationDate, Date aFinishDate) {
        setPublicationDate(aPublicationDate);
        setFinishDate(aFinishDate);
        setInitialFinishDate(aFinishDate);
        setAutomaticOfferAmount(0);

    }


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public List<Bidders> getBidders() { return bidders; }

    public void setBidders(List<Bidders> bidders) { this.bidders = bidders; }

    public String getAuthor(){ return this.author;    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public Date getInitialFinishDate(){return this.initialFinishDate; }

    public void setInitialFinishDate(Date initialFinishDate) {
        this.initialFinishDate = initialFinishDate;
    }


    public long getAutomaticOfferAmount(){ return this.automaticOfferAmount;  }

    public void setAutomaticOfferAmount(long automaticOfferAmount) { this.automaticOfferAmount = automaticOfferAmount;    }



    public void setAutomaticOfferUser(String automaticOfferUser) {this.automaticOfferUser = automaticOfferUser; }



    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }


    public Date getPublicationDate() {
        return publicationDate;
    }
    public void setPublicationDate(Date aDate) {
        this.publicationDate = aDate;
    }



    public Date getFinishDate() {
        return finishDate;
    }
    public void setFinishDate(Date aDate) {
        this.finishDate = aDate;
    }






    public AuctionStatus state() {
        return new StatusProviderOfAnAuction().getState(this);
    }

    private boolean theAuctionMustBeExtended() {
        return theOfferWasMadeWithinTheLastFiveMinutes() && !exceededFortyEightHours();
    }

    private boolean exceededFortyEightHours() {
        Date fiveMinutesAfterTheFinishDate =DateFactory.add(this.finishDate, Calendar.MINUTE,5);
        Date initialFinishDatePlusFortyEightHours = DateFactory.add(this.getInitialFinishDate(), Calendar.DAY_OF_WEEK,2);
        return fiveMinutesAfterTheFinishDate.compareTo(initialFinishDatePlusFortyEightHours) > 0;
    }

    private boolean theOfferWasMadeWithinTheLastFiveMinutes() {
        Date fiveMinutesBeforeTheCurrent =DateFactory.add(new Date(), Calendar.MINUTE,-1);

        return fiveMinutesBeforeTheCurrent.compareTo(this.finishDate) < 0;
    }

    private long fivePercentMoreThanTheCurrentPrice() {
        return (this.price * 5 / 100) + this.price;
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
        return this.automaticOfferAmount != 0 && newPrice <= this.getAutomaticOfferAmount();
    }



    public boolean thereIsAutomaticUser() {
      return !StringUtils.isEmpty(automaticOfferUser);
    }

    public boolean isFinished(){ return this.state().equals(AuctionStatus.COMPLETED); }


    public boolean  isAuthor(String aBidder){ return  aBidder.equalsIgnoreCase(this.getAuthor());}

    public boolean isInProgress(){return this.state().equals(AuctionStatus.IN_PROGRESS); }



}
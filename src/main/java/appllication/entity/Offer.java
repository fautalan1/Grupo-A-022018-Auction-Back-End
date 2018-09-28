package appllication.entity;

import java.util.Date;
import javax.persistence.*;

@Entity
public class Offer {

    @GeneratedValue
    @Id
    private long id;

    private String bidder;

    private Date publicationDate;

    @ManyToOne
    private Auction auction;

    public Offer() {};

    public Offer(String bidder, Auction auction) {
        setBidder(bidder);
        setAuction(auction);
        setPublicationDate(new Date());
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public String getBidder() {
        return bidder;
    }

    public void setBidder(String bidder) {
        this.bidder = bidder;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

}

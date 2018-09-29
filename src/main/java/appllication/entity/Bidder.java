package appllication.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Bidder {

    @GeneratedValue
    @Id
    private long id;

    private String author;

    private long price;

    private Date publicationDate;

    @ManyToOne
    private Auction auction;

    public Bidder(){}

    public Bidder(String bidder, Auction auction) {
        setAuthor(bidder);
        setAuction(auction);
        setPublicationDate(new Date());
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
}

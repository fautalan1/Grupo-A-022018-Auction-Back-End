package entity;

import modelTest.DateFactory;

import java.util.Date;

public class Offer {

    private long id;
    private long auctionId;
    private String author;
    private Date publicationDate;

    Offer(String author, long auctionId) {
        super();
        setAuthor(author);
        setAuctionId(auctionId);
        setPublicationDate(DateFactory.now());
    }

    public long getAuctionId() {
        return auctionId;
    }

    private void setAuctionId(long auctionId) {
        this.auctionId = auctionId;
    }

    public String getAuthor() {
        return author;
    }

    private void setAuthor(String author) {
        this.author = author;
    }

    private void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }
}

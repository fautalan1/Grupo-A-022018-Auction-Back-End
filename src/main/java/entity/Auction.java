package entity;

import modelTest.AuctionStatus;
import modelTest.StatusProviderOfAnAuction;

import java.util.Date;

public class Auction {
    
    public Date publicationDate;
    public Date finishDate;
    
    public Auction(Date aPublicationDate, Date aFinishDate) {
        super();
        setPublicationDate(aPublicationDate);
        setFinishDate(aFinishDate);
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

}

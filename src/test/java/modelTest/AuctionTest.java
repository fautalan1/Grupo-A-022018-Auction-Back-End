package modelTest;

import entity.Auction;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;

public class AuctionTest {

    @Test public void anAuctionWithAPublicationDateOfOneDayLaterThanTheCurrentOneShouldHaveStatusNEW(){
        
        Calendar publicationDateOneDayLaterThanTheCurrent = Calendar.getInstance();
        publicationDateOneDayLaterThanTheCurrent.setTime(new Date());
        publicationDateOneDayLaterThanTheCurrent.add(Calendar.DAY_OF_MONTH, 1);

        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(publicationDateOneDayLaterThanTheCurrent.getTime());

        assertEquals(AuctionStatus.NEW, auction.state());
    }

    @Test public void anAuctionWithAPublicationDateOfOneHourBeforeTheCurrentOneAndAnFinishDateOfOneHourAfterTheCurrentOneShouldHaveStatusIN_PROGRESS(){
        
        Calendar publicationDateOneHourBeforeTheCurrent = Calendar.getInstance();
        publicationDateOneHourBeforeTheCurrent.setTime(new Date());
        publicationDateOneHourBeforeTheCurrent.add(Calendar.HOUR, -1);

        Calendar finishDateOneHourAfterTheCurrent = Calendar.getInstance();
        finishDateOneHourAfterTheCurrent.setTime(new Date());
        finishDateOneHourAfterTheCurrent.add(Calendar.HOUR, 1);

        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(publicationDateOneHourBeforeTheCurrent.getTime());
        auction.setFinishDate(finishDateOneHourAfterTheCurrent.getTime());

        assertEquals(AuctionStatus.IN_PROGRESS, auction.state());
    }

    @Test public void anAuctionWithAnFinishDateOfOneHourBeforeToTheCurrentOneShouldHaveStatusCOMPLETED(){

        Calendar finishDateOneHourBeforeToTheCurrent = Calendar.getInstance();
        finishDateOneHourBeforeToTheCurrent.setTime(new Date());
        finishDateOneHourBeforeToTheCurrent.add(Calendar.HOUR, -1);

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(finishDateOneHourBeforeToTheCurrent.getTime());

        assertEquals(AuctionStatus.COMPLETED, auction.state());
    }

}
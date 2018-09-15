package modelTest;

import entity.Auction;
import entity.User;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.*;

public class AuctionTest {

    @Test public void anAuctionWithAPublicationDateOfOneHourAfterThanTheCurrentOneShouldHaveStatusNew(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(DateFactory.oneHourAfterTheCurrent());

        assertEquals(AuctionStatus.NEW, auction.state());
    }

    @Test public void anAuctionWithAPublicationDateOfOneHourBeforeTheCurrentOneAndAnFinishDateOfOneHourAfterTheCurrentOneShouldHaveStatusInProgress(){
        
        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(DateFactory.oneHourBeforeTheCurrent());
        auction.setFinishDate(DateFactory.oneHourAfterTheCurrent());

        assertEquals(AuctionStatus.IN_PROGRESS, auction.state());
    }

    @Test public void anAuctionWithAnFinishDateOfOneHourBeforeToTheCurrentOneShouldHaveStatusCompleted(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(DateFactory.oneHourBeforeTheCurrent());

        assertEquals(AuctionStatus.COMPLETED, auction.state());
    }

    @Test public void anAuctionShouldHaveAnOfferWhenAnOfferIsMade(){

        Auction auction = AuctionFactory.anyAuction();
        User user = UserFactory.anyUser();

        auction.offer(user.getEmail());

        assertEquals(1, auction.getOffers().size());
    }

    @Test public void shouldBeFivePercentHigherThanThePriceOfAnAuctionWhenAnOfferIsMade(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPrice(100);
        User user = UserFactory.anyUser();

        auction.offer(user.getEmail());

        assertEquals(105, auction.getPrice());
    }

    @Test public void theAuctionShouldBeExtendedFiveMoreMinutesWhenAnOfferIsMadeWithinTheLastFiveMinutesBeforeTheEndOfTheAuction(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(DateFactory.oneMinuteAfterTheCurrent());
        Date oldFinishDate = auction.getFinishDate();
        Date finishDate = DateFactory.addFiveMinutes(oldFinishDate);
        User user = UserFactory.anyUser();

        auction.offer(user.getEmail());

        assertEquals(finishDate, auction.getFinishDate());
    }
}
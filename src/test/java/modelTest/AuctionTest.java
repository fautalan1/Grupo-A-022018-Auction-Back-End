package modelTest;

import entity.Auction;
import model.AuctionFactory;
import model.AuctionStatus;
import model.DateFactory;
import model.UserFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class AuctionTest {

    @Test public void anAuctionWithAPublicationDateOfOneHourAfterThanTheCurrentOneShouldHaveStatusNew(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(DateFactory.oneHourAfterTheCurrent());

        Assert.assertEquals(AuctionStatus.NEW, auction.state());
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

    @Test public void shouldBeFivePercentHigherThanThePriceOfAnAuctionWhenAnOfferIsMade(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPrice(100);

        auction.offer();

        assertEquals(105, auction.getPrice());
    }

    @Test public void anAuctionShouldBeExtendedFiveMoreMinutesWhenAnOfferIsMadeWithinTheLastFiveMinutesBeforeTheEndOfTheAuction(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(DateFactory.oneMinuteAfterTheCurrent());
        Date oldFinishDate = auction.getFinishDate();
        Date finishDate = DateFactory.addFiveMinutes(oldFinishDate);

        auction.offer();

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void anAuctionShouldNotBeExtendedWhenAnOfferWasMadeMoreThanFiveMinutesBeforeTheEndOfTheAuction(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(DateFactory.tenMinutesBeforeTheCurrent());
        Date finishDate = auction.getFinishDate();

        auction.offer();

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void anAuctionShouldNotBeExtendedWhenAnOfferIsMadeWithinTheLastFiveMinutesBeforeTheEndButItExceedsFortyEightHoursOfItsInitialFinishDate(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setInitialFinishDate(DateFactory.fortyEightHoursBeforeTheCurrent());
        auction.setFinishDate(DateFactory.oneMinuteAfterTheCurrent());
        Date finishDate = auction.getFinishDate();

        auction.offer();

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void shouldReturnTrueWhenAnAutomaticOfferWasMadeAndThePriceIsWithinTheMaximumValue(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPrice(100);
        auction.automaticOffer(UserFactory.anyEmail(), 105);

        assertTrue(auction.thereIsToDoTheAutomaticOffer());
    }

    @Test public void shouldReturnFalseWhenAnAutomaticOfferWasNotMade(){

        Auction auction = AuctionFactory.anyAuction();

        assertFalse(auction.thereIsToDoTheAutomaticOffer());
    }

    @Test public void shouldReturnFalseWhenAnAutomaticOfferWasMadeButThePriceIsNotWithinTheMaximumValue(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPrice(100);
        auction.automaticOffer(UserFactory.anyEmail(), 102);

        assertFalse(auction.thereIsToDoTheAutomaticOffer());
    }


    @Test public void anAuctionIsSetAsAutomatic(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setAutomaticOfferUser("Juan");
        assertTrue(auction.thereIsAutomaticUser());
    }

    @Test public void anAuctionDoesNotSetItAsAutomatic(){
        Auction auction = AuctionFactory.anyAuction();
        assertFalse(auction.thereIsAutomaticUser());
    }


    @Test public void anAuctionKnowsIfTerm(){
        Auction auction = AuctionFactory.anyAuction();
        Date time = Calendar.getInstance().getTime();
        auction.setFinishDate(time);
        assertTrue(auction.isFinished());
    }

    @Test public void anAuctionDoesNotKnowsIfTerm(){
        Auction auction = AuctionFactory.anyAuction();
        Date time = Calendar.getInstance().getTime();
        auction.setFinishDate(DateFactory.addFiveMinutes(time));
        assertFalse(auction.isFinished());
    }



}
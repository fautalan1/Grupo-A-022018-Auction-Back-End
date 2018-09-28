package modelTest;

import appllication.entity.Auction;
import appllication.model.AuctionFactory;
import appllication.model.AuctionStatus;
import appllication.model.DateFactory;
import appllication.model.UserFactory;
import org.junit.Assert;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;


public class AuctionTest {

    @Test public void anAuctionWithAPublicationDateOfOneHourAfterThanTheCurrentOneShouldHaveStatusNew(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(DateFactory.add(new Date(),Calendar.HOUR,1));
        Assert.assertEquals(AuctionStatus.NEW, auction.state());
    }

    @Test public void anAuctionWithAPublicationDateOfOneHourBeforeTheCurrentOneAndAnFinishDateOfOneHourAfterTheCurrentOneShouldHaveStatusInProgress(){
        
        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(DateFactory.add(new Date(),Calendar.HOUR,-1));
        auction.setFinishDate(DateFactory.add(new Date(),Calendar.HOUR,1));
        assertEquals(AuctionStatus.IN_PROGRESS, auction.state());
    }

    @Test public void anAuctionWithAnFinishDateOfOneHourBeforeToTheCurrentOneShouldHaveStatusCompleted(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(DateFactory.add(new Date(),Calendar.HOUR,-1));

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
        auction.setFinishDate(DateFactory.add(new Date(),Calendar.MINUTE,1));
        Date oldFinishDate = auction.getFinishDate();
        Date finishDate = DateFactory.add(oldFinishDate,Calendar.MINUTE,5);

        auction.offer();

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void anAuctionShouldNotBeExtendedWhenAnOfferWasMadeMoreThanFiveMinutesBeforeTheEndOfTheAuction(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(DateFactory.add(new Date(),Calendar.MINUTE,-10));
        Date finishDate = auction.getFinishDate();

        auction.offer();

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void anAuctionShouldNotBeExtendedWhenAnOfferIsMadeWithinTheLastFiveMinutesBeforeTheEndButItExceedsFortyEightHoursOfItsInitialFinishDate(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setInitialFinishDate(DateFactory.add(new Date(),Calendar.DAY_OF_WEEK,-2));
        auction.setFinishDate(DateFactory.add(new Date(),Calendar.MINUTE,-1));
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
        auction.setFinishDate(DateFactory.add(new Date(),Calendar.HOUR,-1));
        assertTrue(auction.isFinished());
    }


    @Test public void anAuctionKnowsIfIDoNotFinish(){
        Auction auction = AuctionFactory.anyAuction();

        auction.setFinishDate(DateFactory.add(new Date(),Calendar.MINUTE,5));

        assertFalse(auction.isFinished());
    }

    @Test public void anAuctionKnowsThatTheBidderIsTheAuthor(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setAuthor("Pepita");

        assertTrue(auction.isAuthor("pepita"));
    }


    @Test public void anAuctionKnowsThatTheBidderIsNotTheAuthor(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setAuthor("Pepon");

        assertFalse(auction.isAuthor("pepita"));
    }

    @Test public void  anAuctionIsInProgress(){
        Auction auction = AuctionFactory.anyAuction();
        Date time = Calendar.getInstance().getTime();
        auction.setFinishDate(DateFactory.add(time,Calendar.MINUTE,5));
        assertTrue(auction.isInProgress());
    }





}
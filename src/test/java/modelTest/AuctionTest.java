package modelTest;

import appllication.entity.Auction;
import appllication.model.factory.AuctionFactory;
import appllication.model.auctionState.AuctionStatus;

import org.junit.Assert;

import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.*;


public class AuctionTest {

    @Test public void anAuctionWithAPublicationDateOfOneHourAfterThanTheCurrentOneShouldHaveStatusNew(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(LocalDateTime.now().plusHours(1));
        Assert.assertEquals(AuctionStatus.NEW, auction.state());
    }

    @Test public void anAuctionWithAPublicationDateOfOneHourBeforeTheCurrentOneAndAnFinishDateOfOneHourAfterTheCurrentOneShouldHaveStatusInProgress(){
        
        Auction auction = AuctionFactory.anyAuction();
        auction.setPublicationDate(LocalDateTime.now().minusHours(1));
        auction.setFinishDate(LocalDateTime.now().plusHours(1));
        assertEquals(AuctionStatus.IN_PROGRESS, auction.state());
    }

    @Test public void anAuctionWithAnFinishDateOfOneHourBeforeToTheCurrentOneShouldHaveStatusCompleted(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(LocalDateTime.now().minusHours(1));
        assertEquals(AuctionStatus.COMPLETED, auction.state());
    }

    @Test public void shouldBeFivePercentHigherThanThePriceOfAnAuctionWhenAnOfferIsMade(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setPrice(100);

        auction.offer("userEmail");

        assertEquals(105, auction.getPrice());
    }

    @Test public void anAuctionShouldBeExtendedFiveMoreMinutesWhenAnOfferIsMadeWithinTheLastFiveMinutesBeforeTheEndOfTheAuction(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(LocalDateTime.now().plusMinutes(1));
        LocalDateTime oldFinishDate = auction.getFinishDate()  ;
        LocalDateTime finishDate    = oldFinishDate.plusMinutes(5);

        auction.offer("userEmail");

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void anAuctionShouldNotBeExtendedWhenAnOfferWasMadeMoreThanFiveMinutesBeforeTheEndOfTheAuction(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(LocalDateTime.now().minusMinutes(10));
        LocalDateTime finishDate = auction.getFinishDate();
        auction.offer("userEmail");

        assertEquals(finishDate, auction.getFinishDate());
    }

    @Test public void anAuctionShouldNotBeExtendedWhenAnOfferIsMadeWithinTheLastFiveMinutesBeforeTheEndButItExceedsFortyEightHoursOfItsInitialFinishDate(){

        Auction auction = AuctionFactory.anyAuction();
        auction.setInitialFinishDate(LocalDateTime.now().minusWeeks(2));
        auction.setFinishDate(LocalDateTime.now().minusMinutes(1));
        LocalDateTime finishDate = auction.getFinishDate();
        auction.offer("userEmail");

        assertEquals(finishDate, auction.getFinishDate());
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
        auction.setFinishDate(LocalDateTime.now().minusHours(1));
        assertTrue(auction.isFinished());
    }


    @Test public void anAuctionKnowsIfIDoNotFinish(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(LocalDateTime.now().plusMinutes(5));

        assertFalse(auction.isFinished());
    }

    @Test public void anAuctionKnowsThatTheBidderIsTheAuthor(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setEmailAuthor("Pepita");

        assertTrue(auction.isAuthor("pepita"));
    }


    @Test public void anAuctionKnowsThatTheBidderIsNotTheAuthor(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setEmailAuthor("Pepon");

        assertFalse(auction.isAuthor("pepita"));
    }

    @Test public void  anAuctionIsInProgress(){
        Auction auction = AuctionFactory.anyAuction();
        auction.setFinishDate(LocalDateTime.now().plusMinutes(5));
        assertTrue(auction.isInProgress());
    }




}
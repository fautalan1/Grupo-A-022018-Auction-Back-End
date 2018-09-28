package appllication.model.auctionState;

import appllication.entity.Auction;
import appllication.model.auctionState.AuctionState;
import appllication.model.auctionState.AuctionStatus;

import java.util.Calendar;
import java.util.Date;

public class AuctionStateNew implements AuctionState {

    @Override
    public boolean isTheState(Auction auction) {
        Date now = Calendar.getInstance().getTime();
        return now.compareTo(auction.getPublicationDate()) < 0;
    }

    @Override
    public AuctionStatus getState() {
        return AuctionStatus.NEW;
    }
    
}

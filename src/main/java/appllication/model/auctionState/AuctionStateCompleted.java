package appllication.model.auctionState;

import appllication.entity.Auction;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class AuctionStateCompleted implements AuctionState {

    @Override
    public boolean isTheState(Auction auction) {
        return LocalDateTime.now().compareTo(auction.getFinishDate()) > 0;
    }

    @Override
    public AuctionStatus getState() {
        return AuctionStatus.COMPLETED;
    }
    
}
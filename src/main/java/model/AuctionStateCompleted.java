package model;

import entity.Auction;

import java.util.Calendar;
import java.util.Date;

public class AuctionStateCompleted implements AuctionState {

    @Override
    public boolean isTheState(Auction auction) {
        Date now = Calendar.getInstance().getTime();
        return now.compareTo(auction.getFinishDate()) > 0;
    }

    @Override
    public AuctionStatus getState() {
        return AuctionStatus.COMPLETED;
    }
    
}
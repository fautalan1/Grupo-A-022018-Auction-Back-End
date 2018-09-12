package modelTest;

import entity.Auction;

import java.util.Calendar;
import java.util.Date;

public class AuctionStateInProgress implements AuctionState {

    @Override
    public boolean isTheState(Auction auction) {
        Date now = Calendar.getInstance().getTime();
        return now.compareTo(auction.publicationDate) > 0 && now.compareTo(auction.finishDate) < 0;
    }

    @Override
    public AuctionStatus getState() {
        return AuctionStatus.IN_PROGRESS;
    }

}
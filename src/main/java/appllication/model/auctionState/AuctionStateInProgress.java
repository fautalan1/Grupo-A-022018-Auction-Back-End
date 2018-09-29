package appllication.model.auctionState;

import appllication.entity.Auction;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

public class AuctionStateInProgress implements AuctionState {

    @Override
    public boolean isTheState(Auction auction) {
        LocalDateTime now = LocalDateTime.now();
        return now.compareTo(auction.getPublicationDate()) >= 0 && now.compareTo(auction.getFinishDate()) <= 0;
    }

    @Override
    public AuctionStatus getState() {
        return AuctionStatus.IN_PROGRESS;
    }

}
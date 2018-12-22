package appllication.model.auctionState;

import appllication.entity.Auction;

public interface AuctionState {

    boolean isTheState(Auction auction);

    AuctionStatus getState();
}
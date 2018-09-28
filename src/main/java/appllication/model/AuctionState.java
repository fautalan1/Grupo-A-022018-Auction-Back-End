package appllication.model;

import appllication.entity.Auction;

public interface AuctionState {

    boolean isTheState(Auction auction);

    AuctionStatus getState();
}
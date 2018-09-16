package model;

import entity.Auction;

public interface AuctionState {

    boolean isTheState(Auction auction);

    AuctionStatus getState();
}
package modelTest;

import entity.Auction;

public interface AuctionState {

    public boolean isTheState(Auction auction);

    public AuctionStatus getState();
}
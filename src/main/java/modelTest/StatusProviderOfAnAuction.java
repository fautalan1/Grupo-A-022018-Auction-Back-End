package modelTest;

import entity.Auction;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatusProviderOfAnAuction {

    List<AuctionState> auctionStates;
    
    public StatusProviderOfAnAuction() {
        super();
        this.auctionStates = Arrays.asList(new AuctionStateNew(), new AuctionStateInProgress(), new AuctionStateCompleted());
    }

    public AuctionStatus getState(Auction auction) {

        return this.auctionStates.stream().filter(auctionState -> auctionState.isTheState(auction)).collect(Collectors.toList()).get(0).getState();
    }
}


package appllication.model.auctionState;

import appllication.entity.Auction;
import appllication.model.auctionState.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StatusProviderOfAnAuction {

    private List<AuctionState> auctionStates;
    
    public StatusProviderOfAnAuction() {

        this.auctionStates = Arrays.asList(new AuctionStateNew(), new AuctionStateInProgress(), new AuctionStateCompleted());
    }

    public AuctionStatus getState(Auction auction) {

        return this.auctionStates.stream().filter(auctionState ->   auctionState.isTheState(auction)).
                                                                    collect(Collectors.toList()).
                                                                    get(0).
                                                                    getState();
    }
}


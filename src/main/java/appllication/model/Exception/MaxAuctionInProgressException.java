package appllication.model.Exception;

public class MaxAuctionInProgressException  extends  RuntimeException {
    public MaxAuctionInProgressException(String anException) {
        super(anException);
    }
}

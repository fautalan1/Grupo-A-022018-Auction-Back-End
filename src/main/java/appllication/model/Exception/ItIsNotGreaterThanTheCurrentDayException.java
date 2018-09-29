package appllication.model.Exception;

public class ItIsNotGreaterThanTheCurrentDayException extends  RuntimeException{
    public ItIsNotGreaterThanTheCurrentDayException(String anException){
        super(anException);
    }
}

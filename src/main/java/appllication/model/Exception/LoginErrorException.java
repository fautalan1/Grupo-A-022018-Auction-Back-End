package appllication.model.Exception;

public class LoginErrorException extends  RuntimeException {
    public LoginErrorException(String anException) {
        super(anException);
    }
}
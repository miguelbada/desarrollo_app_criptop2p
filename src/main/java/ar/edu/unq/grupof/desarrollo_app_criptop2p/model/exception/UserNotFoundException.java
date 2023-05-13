package ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message) {
        super(message);
    }
}

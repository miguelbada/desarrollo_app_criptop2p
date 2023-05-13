package ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception;

public class TransactionNotFoundException extends RuntimeException{

    public TransactionNotFoundException(String message) {
        super(message);
    }
}

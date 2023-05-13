package ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception;

public class TransactionNotProcessException extends RuntimeException{
    public TransactionNotProcessException(String message) {
        super(message);
    }
}

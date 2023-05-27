package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.OperationType;

public class TransactionRequestDTO {
    private String cripto;
    private Double criptoQuantity;
    private Double criptoQuote;
    private String user;
    private String userTransaction;
    private OperationType type;

    public TransactionRequestDTO() {
    }

    public String getCripto() {
        return cripto;
    }

    public void setCripto(String cripto) {
        this.cripto = cripto;
    }

    public Double getCriptoQuantity() {
        return criptoQuantity;
    }

    public void setCriptoQuantity(Double criptoQuantity) {
        this.criptoQuantity = criptoQuantity;
    }

    public Double getCriptoQuote() {
        return criptoQuote;
    }

    public void setCriptoQuote(Double criptoQuote) {
        this.criptoQuote = criptoQuote;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(String userTransaction) {
        this.userTransaction = userTransaction;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }
}

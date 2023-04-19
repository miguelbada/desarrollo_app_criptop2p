package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor
public class Intention {
    private Cripto cripto;
    private Double criptoQuantity;
    private Double criptoQuote;
    private Double argentineCurrency;
    private User user;
    private OperationType type;
    private StateProcess stateProcess;

    private User userTransaction;

    public Intention() {
        this.stateProcess = StateProcess.ACTIVE;
    }

    public Cripto getCripto() {
        return cripto;
    }

    public void setCripto(Cripto cripto) {
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

    public Double getArgentineCurrency() {
        return argentineCurrency;
    }

    public void setArgentineCurrency(Double argentineCurrency) {
        argentineCurrency = argentineCurrency;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public OperationType getType() {
        return type;
    }

    public void setType(OperationType type) {
        this.type = type;
    }

    public StateProcess getStateProcess() {
        return stateProcess;
    }

    public void setStateProcess(StateProcess stateProcess) {
        this.stateProcess = stateProcess;
    }

    public User getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(User userTransaction) {
        this.userTransaction = userTransaction;
    }

    public Boolean isStateProcess(StateProcess stateProcess) {
        return Objects.equals(getStateProcess(), stateProcess);
    }

    public Boolean isType(OperationType operationType) {
        return Objects.equals(getType(), operationType);
    }
}

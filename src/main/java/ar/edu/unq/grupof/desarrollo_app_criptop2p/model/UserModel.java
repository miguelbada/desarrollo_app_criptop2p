package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;

@Builder
@AllArgsConstructor // para generar un constructor con todos los parametros
@Entity
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String name;
    private String lastName;
    private String email;
    private String address;
    private String password;
    private String cvuMercadoPago;
    private String cryptoWallet;
    private Integer doneOperations;
    private Integer reputation;

    public UserModel() {
        this.doneOperations = 0;
        this.reputation = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCvuMercadoPago() {
        return cvuMercadoPago;
    }

    public void setCvuMercadoPago(String cvuMercadoPago) {
        this.cvuMercadoPago = cvuMercadoPago;
    }

    public String getCryptoWallet() {
        return cryptoWallet;
    }

    public void setCryptoWallet(String cryptoWallet) {
        this.cryptoWallet = cryptoWallet;
    }

    public Integer getDoneOperations() {
        return doneOperations;
    }

    public void setDoneOperations(Integer doneOperations) {
        this.doneOperations = doneOperations;
    }

    public void addDoneOperation() {
        this.doneOperations ++;
    }

    public Integer getReputation() {
        return reputation;
    }

    public void setReputation(Integer reputation) {
        this.reputation = reputation;
    }

    public void addReputation(Integer points) {
        this.reputation += points;
    }

    public void discountReputation(Integer point) {
        if(point <= reputation) {
            this.reputation -= point;
        } else {
            this.reputation = 0;
        }

    }

    public String fullName() {
        return name + " " + lastName;
    }

    public void processIntentionTo(Intention intention) {
        if(isBuyer(intention) || isSeller(intention)) {
            intention.setStateProcess(StateProcess.IN_PROCESS);
            intention.setUserTransaction(this);
        }
    }

    public void makeTransfer(Intention intention) {
        if(intention.isStateProcess(StateProcess.IN_PROCESS)
                && ((intention.isType(OperationType.PURCHASE)
                    && isUserIntention(intention))
                    || (isUserTransaction(intention)
                    && intention.isType(OperationType.SALE)))) {
            intention.setStateProcess(StateProcess.MAKE_TRANSFER);
        }
    }

    public void confirmReception(Intention intention) {
        if(intention.isStateProcess(StateProcess.MAKE_TRANSFER)
                && ((intention.isType(OperationType.SALE)
                        && isUserIntention(intention))
                        || (isUserTransaction(intention)
                        && intention.isType(OperationType.PURCHASE)))) {
            intention.setStateProcess(StateProcess.PROCESSED);
            intention.getUser().addDoneOperation();
            intention.getUserTransaction().addDoneOperation();
        }
    }

    public void cancelIntention(Intention intention) {
        if(isUserIntention(intention) && intention.isStateProcess(StateProcess.ACTIVE)) {
            intention.setStateProcess(StateProcess.CANCEL);
        } else if((intention.isStateProcess(StateProcess.IN_PROCESS)
                || intention.isStateProcess(StateProcess.MAKE_TRANSFER)) && isUserIntention(intention)) {
            intention.setStateProcess(StateProcess.CANCEL);
            intention.getUser().discountReputation(20);
        } else if ((intention.isStateProcess(StateProcess.IN_PROCESS)
                || intention.isStateProcess(StateProcess.MAKE_TRANSFER)) && isUserTransaction(intention)) {
            intention.setStateProcess(StateProcess.CANCEL);
            intention.getUserTransaction().discountReputation(20);
        }

    }

    public Boolean isBuyer(Intention intention) {

        return intention.isStateProcess(StateProcess.ACTIVE)
                && intention.getType() == OperationType.PURCHASE
                && !isUserIntention(intention);
    }

    public Boolean isSeller(Intention intention) {

        return intention.isStateProcess(StateProcess.ACTIVE)
                && intention.getType() == OperationType.SALE
                && !isUserIntention(intention);
    }

    public Boolean isUserIntention(Intention intention) {
        return Objects.equals(intention.getUser().fullName(), this.fullName());
    }

    public Boolean isUserTransaction(Intention intention) {
        return Objects.equals(intention.getUserTransaction().fullName(), fullName());
    }

}

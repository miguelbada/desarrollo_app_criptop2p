package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Builder
@AllArgsConstructor
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Basic
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name="cripto_id", nullable=false)
    private Cripto cripto;
    private Double criptoQuantity;
    private Double criptoQuote;
    private Double argentineCurrency;

    @ManyToOne
    @JoinColumn(name="user_id")
    private UserModel user;

    @Enumerated(EnumType.STRING)
    private OperationType type;

    @Enumerated(EnumType.STRING)
    private StateProcess stateProcess;

    @ManyToOne
    @JoinColumn(name="user_transaction_id")
    private UserModel userTransaction;

    public Transaction() {
        this.dateTime = getCurrentDate();
        this.stateProcess = StateProcess.ACTIVE;
    }

    public Transaction(Cripto cripto, UserModel user, Double criptoQuantity, Double criptoQuote, OperationType type) {
        this.dateTime = getCurrentDate();
        this.stateProcess = StateProcess.ACTIVE;
        this.cripto = cripto;
        this.user = user;
        this.criptoQuantity = criptoQuantity;
        this.criptoQuote = criptoQuote;
        this.argentineCurrency = cripto.getArgentineCurrencyQuotation() * criptoQuote;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
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
        this.argentineCurrency = argentineCurrency;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
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

    public UserModel getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(UserModel userTransaction) {
        this.userTransaction = userTransaction;
    }

    public Boolean isStateProcess(StateProcess stateProcess) {
        return Objects.equals(getStateProcess(), stateProcess);
    }

    public Boolean isType(OperationType operationType) {
        return Objects.equals(getType(), operationType);
    }

    public boolean isSafePrice() {
        if(!Objects.equals(cripto, null) ) {
            Double priceCripto = cripto.getPrice();
            Double priceIntention = this.criptoQuote;
            Double percent = (priceIntention * 100.0) / priceCripto;

            return 105.0 >= percent && 95 <= percent;
        }
        return false;
    }

    private LocalDateTime getCurrentDate() {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS");
        String text = date.format(formatter);

        return LocalDateTime.parse(text, formatter);
    }

}

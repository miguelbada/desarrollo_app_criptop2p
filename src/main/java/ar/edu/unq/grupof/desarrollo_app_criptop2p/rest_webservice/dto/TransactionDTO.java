package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.OperationType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.StateProcess;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionDTO {
    private Long id;
    private LocalDateTime dateTime;
    private String cripto;
    private Double criptoQuantity;
    private Double criptoQuote;
    private Double argentineCurrency;
    private String user;
    private String userTransaction;
    private OperationType type;
    private StateProcess stateProcess;

    public TransactionDTO() {
        //Use for Hibernate
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

    public Double getArgentineCurrency() {
        return argentineCurrency;
    }

    public void setArgentineCurrency(Double argentineCurrency) {
        this.argentineCurrency = argentineCurrency;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
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

    public String getUserTransaction() {
        return userTransaction;
    }

    public void setUserTransaction(String userTransaction) {
        this.userTransaction = userTransaction;
    }

    public TransactionDTO mapperToDTO(Transaction transaction) {
        ModelMapper mapper = new ModelMapper();
        TypeMap<Transaction, TransactionDTO> propertyMapper = mapper.createTypeMap(Transaction.class, TransactionDTO.class);
        propertyMapper.addMappings(modelMap -> modelMap.map(src -> src.getCripto().getSymbol(), TransactionDTO::setCripto));
        propertyMapper.addMappings(modelMap -> modelMap.map(src -> src.getUser().getUsername(), TransactionDTO::setUser));
        propertyMapper.addMappings(modelMap -> modelMap.map(src -> src.getUserTransaction().getUsername(), TransactionDTO::setUserTransaction));

        return mapper.map(transaction, TransactionDTO.class);
    }

    public List<TransactionDTO> mapperToListDTO(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction: transactions) {
            transactionDTOs.add(this.mapperToDTO(transaction));
        }

        return transactionDTOs;
    }

}

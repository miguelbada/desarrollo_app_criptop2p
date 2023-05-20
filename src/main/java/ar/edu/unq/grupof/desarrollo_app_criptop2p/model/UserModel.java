package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.TransactionNotProcessException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

@Builder
@AllArgsConstructor // para generar un constructor con todos los parametros
@Entity
public class UserModel implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany
    @JoinTable(name = "user_model_rol",
            joinColumns = @JoinColumn(name = "user_model_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;
    private String name;
    private String lastName;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;
    private String address;
    private String password;
    private String cvuMercadoPago;

    @Column(nullable = false, unique = true)
    private String cryptoWallet;
    private Integer doneOperations;
    private Integer reputation;

    public UserModel() {
        this.roles = new ArrayList<>();
        this.doneOperations = 0;
        this.reputation = 0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role role) {
        this.roles.add(role);
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

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
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

    public void processIntentionTo(Transaction intention) {
        if(isBuyer(intention) || isSeller(intention)) {
            intention.setStateProcess(StateProcess.IN_PROCESS);
            intention.setUserTransaction(this);
        }
    }

    public void makeTransfer(Transaction intention) {
        if(intention.isStateProcess(StateProcess.IN_PROCESS)
                && ((intention.isType(OperationType.BUY)
                    && isUserIntention(intention))
                    || (isUserTransaction(intention)
                    && intention.isType(OperationType.SALE)))) {
            intention.setStateProcess(StateProcess.MAKE_TRANSFER);
        }
    }

    public void confirmReception(Transaction intention) {
        try {
            if(!intention.isSafePrice()) {
                intention.setStateProcess(StateProcess.CANCEL);
                throw new TransactionNotProcessException("the transaction is canceled due to price difference");
            } else if(intention.isStateProcess(StateProcess.MAKE_TRANSFER)
                    && ((intention.isType(OperationType.SALE)
                    && isUserIntention(intention))
                    || (isUserTransaction(intention)
                    && intention.isType(OperationType.BUY)))) {
                intention.setStateProcess(StateProcess.PROCESSED);
                intention.getUser().addDoneOperation();
                intention.getUserTransaction().addDoneOperation();
            }

        } catch (Exception error) {
            System.out.println(error);
        }

    }

    public void cancelIntention(Transaction intention) {
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

    public Transaction createTransaction(Cripto cripto, Double criptoQuantity, Double criptoQuote, Double argentineCurrency, OperationType type) {
        return new Transaction(cripto, this, criptoQuantity, criptoQuote, argentineCurrency, type);
    }

    private Boolean isBuyer(Transaction intention) {

        return intention.isStateProcess(StateProcess.ACTIVE)
                && intention.getType() == OperationType.BUY
                && !isUserIntention(intention);
    }

    private Boolean isSeller(Transaction intention) {

        return intention.isStateProcess(StateProcess.ACTIVE)
                && intention.getType() == OperationType.SALE
                && !isUserIntention(intention);
    }

    private Boolean isUserIntention(Transaction intention) {
        return Objects.equals(intention.getUser().fullName(), this.fullName());
    }

    private Boolean isUserTransaction(Transaction intention) {
        return Objects.equals(intention.getUserTransaction().fullName(), fullName());
    }

}

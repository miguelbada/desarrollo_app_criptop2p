package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Role;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.RoleType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import jakarta.validation.constraints.*;
import org.modelmapper.ModelMapper;

public class UserModelDTO {
    @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters")
    private String name;

    @Size(min = 3, max = 30, message = "lastname must be between 3 and 30 characters")
    private String lastName;

    @Email(message = "Invalid email address format")
    @Size(min = 10, max = 30, message = "email address must be between 10 and 30 characters")
    private String email;

    @Size(min = 10, max = 30, message = "address must be between 10 and 30 characters")
    private String address;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{6,20}$",
            message = "Invalid password format")
    private String password;

    @Size(min = 22, max = 22, message = "The length of cvuMercadoPago must have of 22 characters")
    private String cvuMercadoPago;

    @Size(min = 8, max = 8, message = "The length of cryptoWallet must have of 8 characters")
    private String cryptoWallet;

    public UserModelDTO() {
        //Use for Hibernate
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

    public UserModel mapperToEntity() {
        ModelMapper mapper = new ModelMapper();
        UserModel user = mapper.map(this, UserModel.class);

        user.setUsername(user.getEmail());

        return user;
    }

}
package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import jakarta.validation.constraints.NotNull;

public class UserModelDTO {
    @NotNull(message = "username shouldn't be null")
    private String name;
    private String lastName;

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
}
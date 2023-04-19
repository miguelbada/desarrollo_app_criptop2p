package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

public class UserModelDTO {
    private String name;
    private String lastName;

    public UserModelDTO() {
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
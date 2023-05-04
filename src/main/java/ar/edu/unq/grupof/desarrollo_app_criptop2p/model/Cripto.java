package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@Entity
public class Cripto {
    @Id
    private String symbol;

    @Basic
    private LocalDateTime dateTime;
    private Double price;
    private Double argentineCurrency;

    public Cripto() {
        this.dateTime = LocalDateTime.now();
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getArgentineCurrency() {
        return argentineCurrency;
    }

    public void setArgentineCurrency(Double argentinePesos) {
        this.argentineCurrency = price * argentinePesos;
    }
}

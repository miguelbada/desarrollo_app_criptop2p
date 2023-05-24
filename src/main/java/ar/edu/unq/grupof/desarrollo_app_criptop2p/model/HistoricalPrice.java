package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class HistoricalPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String symbol;

    @Basic
    private LocalDateTime dateTime;

    private Double price;

    public HistoricalPrice() {
        this.dateTime = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

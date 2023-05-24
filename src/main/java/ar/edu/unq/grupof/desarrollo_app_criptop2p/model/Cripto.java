package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HistoricalPrice> historicalPrices;

    public Cripto() {
        this.dateTime = LocalDateTime.now();
        this.historicalPrices = new ArrayList<>();
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

    public List<HistoricalPrice> getHistoricalPrices() {
        return historicalPrices;
    }

    public void setHistoricalPrices(List<HistoricalPrice> historicalPrices) {
        this.historicalPrices = historicalPrices;
    }

    public void generateHistoricalPrice() {
        HistoricalPrice historicalPrice = new HistoricalPrice();
        historicalPrice.setSymbol(this.symbol);
        historicalPrice.setDateTime(this.dateTime);
        historicalPrice.setPrice(this.price);

         this.historicalPrices.add(historicalPrice);
    }

    public List<HistoricalPrice> getLas24tHs() {
        return this.historicalPrices
                .stream()
                .filter(h -> h.getDateTime().isAfter(LocalDateTime.now().minusHours(24)))
                .collect(Collectors.toList());
    }

}

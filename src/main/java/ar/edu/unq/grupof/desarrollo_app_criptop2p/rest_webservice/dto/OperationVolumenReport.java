package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OperationVolumenReport {
    private LocalDateTime dateTime;
    private Double totalPrice;
    private Double totalArgentinePesos;
    private HashMap<String, CriptoReport> criptos;

    public OperationVolumenReport() {
        this.dateTime = LocalDateTime.now();
        this.totalPrice = 0.0;
        this.totalArgentinePesos = 0.0;
        this.criptos = new HashMap<>();
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Double getTotalArgentinePesos() {
        return totalArgentinePesos;
    }

    public void setTotalArgentinePesos(Double totalArgentinePesos) {
        this.totalArgentinePesos = totalArgentinePesos;
    }

    public HashMap<String, CriptoReport> getCriptos() {
        return criptos;
    }

    public void setCriptos(HashMap<String, CriptoReport> criptos) {
        this.criptos = criptos;
    }

    public void addPrice(Double price) {
        this.totalPrice += price;
    }

    public void addArgentinePesos(Double pesos) {
        this.totalArgentinePesos += pesos;
    }

    public void addCripto(Transaction transaction) {
        String key = transaction.getCripto().getSymbol();
        CriptoReport report = new CriptoReport();

        report.setCriptoQuantity(transaction.getCriptoQuantity());
        report.setCriptoQuote(transaction.getCriptoQuote());
        report.setArgentineCurrency(transaction.getArgentineCurrency());

        if (this.criptos.containsKey(key)) {
            Double criptoQuantity = this.criptos.get(key).getCriptoQuantity() + transaction.getCriptoQuantity();
            Double criptoQuote = this.criptos.get(key).getCriptoQuote() + transaction.getCriptoQuote();
            Double argentineCurrency = this.criptos.get(key).getArgentineCurrency() + transaction.getArgentineCurrency();

            report.setCriptoQuantity(criptoQuantity);
            report.setCriptoQuote(criptoQuote);
            report.setArgentineCurrency(argentineCurrency);

            this.criptos.put(key, report);
        } else {
            this.criptos.put(key, report);
        }
    }
}

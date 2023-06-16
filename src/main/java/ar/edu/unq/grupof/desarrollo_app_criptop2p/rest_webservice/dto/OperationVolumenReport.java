package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationVolumenReport {
    private LocalDateTime dateTime;
    private Double totalPrice;
    private Double totalArgentinePesos;
    private List<CriptoReport> criptos;

    public OperationVolumenReport() {
        this.dateTime = LocalDateTime.now();
        this.totalPrice = 0.0;
        this.totalArgentinePesos = 0.0;
        this.criptos = new ArrayList<>();
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

    public List<CriptoReport> getCriptos() {
        return criptos;
    }

    public void setCriptos(List<CriptoReport> criptos) {
        this.criptos = criptos;
    }

    public void addPrice(Double price) {
        this.totalPrice += price;
    }

    public void addArgentinePesos(Double pesos) {
        this.totalArgentinePesos += pesos;
    }

    public void addCripto(Transaction transaction) {
        Cripto cripto = transaction.getCripto();
        CriptoReport report = new CriptoReport();

        report.setSymbol(cripto.getSymbol());
        report.setCriptoQuantity(transaction.getCriptoQuantity());
        report.setCriptoQuote(cripto.getPrice());
        report.setArgentineCurrency(cripto.getArgentineCurrency());

        this.criptos.add(report);
    }
}

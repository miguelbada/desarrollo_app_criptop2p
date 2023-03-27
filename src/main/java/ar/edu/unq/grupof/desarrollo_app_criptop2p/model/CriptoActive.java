package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import java.time.LocalDateTime;

public class CriptoActive {
    private String symbol;
    private LocalDateTime dateTime;

    public CriptoActive() {
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

}

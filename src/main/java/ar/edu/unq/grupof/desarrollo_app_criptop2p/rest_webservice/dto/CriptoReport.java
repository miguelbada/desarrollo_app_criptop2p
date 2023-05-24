package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

public class CriptoReport {
    private Double criptoQuantity;
    private Double criptoQuote;
    private Double argentineCurrency;

    public CriptoReport() {
        this.criptoQuantity = 0.0;
        this.criptoQuote = 0.0;
        this.argentineCurrency = 0.0;
    }

    public Double getCriptoQuantity() {
        return criptoQuantity;
    }

    public void setCriptoQuantity(Double criptoQuantity) {
        this.criptoQuantity = criptoQuantity;
    }

    public Double getCriptoQuote() {
        return criptoQuote;
    }

    public void setCriptoQuote(Double criptoQuote) {
        this.criptoQuote = criptoQuote;
    }

    public Double getArgentineCurrency() {
        return argentineCurrency;
    }

    public void setArgentineCurrency(Double argentineCurrency) {
        this.argentineCurrency = argentineCurrency;
    }
}

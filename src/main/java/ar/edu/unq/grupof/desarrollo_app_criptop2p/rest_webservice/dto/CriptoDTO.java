package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CriptoDTO {
    private String symbol;
    private LocalDateTime dateTime;
    private Double price;
    private Double argentineCurrency;
    private final ModelMapper mapper = new ModelMapper();

    public CriptoDTO() {
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

    public void setArgentineCurrency(Double argentineCurrency) {
        this.argentineCurrency = argentineCurrency;
    }

    public CriptoDTO mapperToEntity(Cripto cripto) {
        return this.mapper.map(cripto, CriptoDTO.class);
    }

    public List<CriptoDTO> mapperToListCriptoDTO(List<Cripto> criptos) {
        List<CriptoDTO> criptoDTOs = new ArrayList<>();

        for (Cripto cripto: criptos) {
            criptoDTOs.add(mapperToEntity(cripto));
        }

        return criptoDTOs;
    }
}

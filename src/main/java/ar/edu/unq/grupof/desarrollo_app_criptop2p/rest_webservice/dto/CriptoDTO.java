package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record CriptoDTO(String symbol, LocalDateTime dateTime, Double price, Double argentineCurrency) {
}

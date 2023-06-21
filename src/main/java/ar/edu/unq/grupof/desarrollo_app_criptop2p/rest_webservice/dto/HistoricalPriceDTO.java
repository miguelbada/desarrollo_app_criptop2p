package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import java.time.LocalDateTime;

public record HistoricalPriceDTO(String symbol, LocalDateTime dateTime, Double price) {
}

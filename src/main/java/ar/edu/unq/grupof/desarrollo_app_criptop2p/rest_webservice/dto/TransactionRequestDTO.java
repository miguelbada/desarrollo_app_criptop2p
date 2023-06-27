package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.OperationType;

public record TransactionRequestDTO(String cripto, Double criptoQuantity, Double criptoQuote, OperationType type) {
}

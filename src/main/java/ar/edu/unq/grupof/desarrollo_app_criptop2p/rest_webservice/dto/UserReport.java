package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import java.time.LocalDateTime;

public record UserReport(Long id, LocalDateTime startDate, LocalDateTime endDate) {
}

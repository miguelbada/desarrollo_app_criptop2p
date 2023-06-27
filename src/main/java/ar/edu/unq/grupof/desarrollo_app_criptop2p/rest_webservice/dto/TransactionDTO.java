package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.OperationType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.StateProcess;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public record TransactionDTO(
        Long id,
        LocalDateTime dateTime,
        String cripto,
        Double criptoQuantity,
        Double criptoQuote,
        Double argentineCurrency,
        String user,
        String userTransaction,
        OperationType type,
        StateProcess stateProcess) {
}

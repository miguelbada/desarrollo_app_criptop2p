package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ScheduledTaskService {
    @Autowired
    private CriptoService service;

    //@Scheduled(fixedRate = 10000)
    @Transactional
    public void initializeCriptosScheduled() {
        List<Cripto> criptosBiList = service.getBinanceCryptos();

        for (Cripto cripto: criptosBiList) {
            service.saveCripto(updateCripto(cripto, service.getCriptoBySymbol(cripto.getSymbol())));
        }

    }

    private Cripto updateCripto(Cripto criptoBi, Cripto criptoDB) {
        criptoDB.setDateTime(criptoBi.getDateTime());
        criptoDB.setPrice(criptoBi.getPrice());
        criptoDB.setArgentineCurrency(criptoBi.getArgentineCurrency());
        criptoDB.generateHistoricalPrice();

        return criptoDB;
    }

}

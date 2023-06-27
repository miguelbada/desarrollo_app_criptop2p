package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.CriptoRepository;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.cache.annotation.Cacheable;
import java.util.*;

@Service
public class CriptoServiceImpl implements CriptoService {
    @Autowired
    private CriptoRepository repository;

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    @Cacheable(value = "allCriptoCache")
    public List<Cripto> findAllCriptos() {
        return repository.findAll();
    }

    @Override
    public Cripto saveCripto(Cripto cripto) {
        return repository.save(cripto);
    }

    @Override
    public Cripto getCriptoBySymbol(String symbol) {
        return repository.findBySymbol(symbol).orElseThrow(() -> new Error("Cripto not found by symbol: " + symbol));
    }

    @Override
    public void updateAllCripto(List<Cripto> criptos) {
        repository.saveAll(criptos);
    }

    @Override
    public List<Cripto> getBinanceCryptos() {
        String uri = "https://www.binance.com/api/v3/ticker/price";
        RestTemplate restTemplate = new RestTemplate();
        Cripto[] prices = restTemplate.getForObject(uri, Cripto[].class);

        if (!ArrayUtils.isEmpty(prices) || !Objects.equals(prices, null)) {

            return Arrays.stream(prices).filter(c -> this.getSimbols().contains(c.getSymbol())).toList();
        } else {
            return null;
        }

    }

    private Double getUsdExchangeRate() {
        String uri = "https://api.exchangerate.host/latest?base=USD&symbols=ARS";
        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, HashMap<Object, Object>> response;

        response = restTemplate.getForObject(uri, HashMap.class);

        if(response != null) {
            return (Double) response.get("rates").get("ARS");
        } else {
            return null;
        }
    }

    private List<String> getSimbols() {
        return new ArrayList<>(List.of(
                "ALICEUSDT",
                "MATICUSDT",
                "AXSUSDT",
                "AAVEUSDT",
                "ATOMUSDT",
                "NEOUSDT",
                "DOTUSDT",
                "ETHUSDT",
                "CAKEUSDT",
                "BTCUSDT",
                "BNBUSDT",
                "ADAUSDT",
                "TRXUSDT",
                "AUDIOUSDT"
        ));
    }

    @Override
    public List<Cripto> initialiceCryptos() {
        List<Cripto> criptos = this.getBinanceCryptos();
        Double argentinePesos = this.getUsdExchangeRate();

        if(Objects.equals(criptos, null) || Objects.equals(argentinePesos, null)) {
            logger.error("error connecting to binance API, data will be simulated");

            DataDemo data = new DataDemo();

            criptos = data.getCriptos();
            argentinePesos = data.getArgentineCurrency();
        }

        this.prepareCriptos(criptos, argentinePesos);

        return repository.saveAll(criptos);
    }

    private void prepareCriptos(List<Cripto> criptos, Double argentinePesos) {

        for (Cripto crypto : criptos) {
            crypto.setArgentineCurrency(argentinePesos);
            crypto.generateHistoricalPrice();
        }

    }

}

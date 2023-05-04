package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.CriptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CriptoServiceImpl implements CriptoService {
    @Autowired
    private CriptoRepository repository;

    @Override
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
    public List<Cripto> getBinanceCryptos() {
        Double argentinePesos = this.getUsdExchangeRate();
        String uri = "https://www.binance.com/api/v3/ticker/price";
        RestTemplate restTemplate = new RestTemplate();
        Cripto[] prices = restTemplate.getForObject(uri, Cripto[].class);

        List<Cripto> cryptos = Arrays.stream(prices).filter(c -> this.getSimbols().contains(c.getSymbol())).collect(Collectors.toList());

        for (Cripto crypto : cryptos) {
            crypto.setArgentineCurrency(argentinePesos);
        }

        return cryptos;
    }

    private Double getUsdExchangeRate() {
        String uri = "https://api.exchangerate.host/latest?base=USD&symbols=ARS";
        RestTemplate restTemplate = new RestTemplate();

        HashMap<String, HashMap<Object, Object>> response;

        response = restTemplate.getForObject(uri, HashMap.class);

        return (Double) response.get("rates").get("ARS");
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

}

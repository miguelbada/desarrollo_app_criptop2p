package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public interface CriptoService {
    List<Cripto> getBinanceCryptos();
    List<Cripto> findAllCriptos();
    Cripto saveCripto(Cripto cripto);
    Cripto getCriptoBySymbol(String symbol);
}

package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.HistoricalPrice;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.CriptoDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.CriptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cripto")
public class CriptoController {
    @Autowired
    private CriptoService service;
    private final CriptoDTO criptoDTO = new CriptoDTO();

    @GetMapping("/all")
    public ResponseEntity<List<CriptoDTO>> getAllCriptos() {
        List<Cripto> criptos = service.findAllCriptos();

        return ResponseEntity.ok().body(criptoDTO.mapperToListCriptoDTO(criptos));
    }

    @PostMapping("/create")
    public ResponseEntity<Cripto> saveCripto(@RequestBody Cripto cripto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCripto(cripto));
    }

    @GetMapping("/24hs/{symbol}")
    public ResponseEntity<List<HistoricalPrice>> getLast24hs(@PathVariable String symbol) {
        return ResponseEntity.ok().body(service.getCriptoBySymbol(symbol).getLas24tHs());

    }
}

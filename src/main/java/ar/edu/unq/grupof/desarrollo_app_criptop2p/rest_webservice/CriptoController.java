package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.HistoricalPrice;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.CriptoDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.HistoricalPriceDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.CriptoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/cripto")
@Tag(name = "Cripto")
public class CriptoController {
    @Autowired
    private CriptoService service;
    private final CriptoDTO criptoDTO = new CriptoDTO();


    @Operation(summary = "Get all criptoactives", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = CriptoDTO.class)))),
            @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/all")
    public ResponseEntity<List<CriptoDTO>> getAllCriptos() {
        List<Cripto> criptos = service.findAllCriptos();

        return ResponseEntity.ok().body(criptoDTO.mapperToListCriptoDTO(criptos));
    }


    @Operation(summary = "Register a new criptoactive", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cripto.class))),
            @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @PostMapping("/create")
    public ResponseEntity<Cripto> saveCripto(@RequestBody Cripto cripto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveCripto(cripto));
    }


    @Operation(summary = "shows the quotes of the last 24 hours of a given crypto asset", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = HistoricalPrice.class)))),
            @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/last24hs/{symbol}")
    public ResponseEntity<List<HistoricalPriceDTO>> getLast24hs(@PathVariable @Parameter(description = "The name of the criptoactive to find") String symbol) {
        return ResponseEntity.ok().body(entityListHistoricalToDtoList(service.getCriptoBySymbol(symbol).getLas24tHs()));
    }

    private List<HistoricalPriceDTO> entityListHistoricalToDtoList(List<HistoricalPrice> historicalPrices) {
        List<HistoricalPriceDTO> dtoList = new ArrayList<>();

        for (HistoricalPrice h: historicalPrices) {
            dtoList.add(new HistoricalPriceDTO(h.getSymbol(), h.getDateTime(), h.getPrice()));
        }

        return dtoList;
    }

}

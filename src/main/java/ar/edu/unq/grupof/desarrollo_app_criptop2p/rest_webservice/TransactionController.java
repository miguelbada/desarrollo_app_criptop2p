package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.*;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.TransactionDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.TransactionRequestDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.CriptoService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
@Tag(name = "Transaction")
public class TransactionController {
    @Autowired
    private TransactionService serviceTransaction;

    @Autowired
    private CriptoService serviceCripto;

    @Autowired
    private AuthenticationService authService;

    @Operation(summary = "Get all transactions", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransactionDTO.class)))),
            @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = serviceTransaction.findAllTransactions();

        return ResponseEntity.ok().body(this.mapperToListDTO(transactions));
    }


    @Operation(summary = "Register a new transaction", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = TransactionDTO.class))),
            @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> saveTransaction(HttpServletRequest request, @RequestBody TransactionRequestDTO transactionRequest) {

        UserModel user = authService.getUserToken(request);
        Cripto cripto = serviceCripto.getCriptoBySymbol(transactionRequest.cripto());

        Transaction transaction = user.createTransaction(
                cripto,
                transactionRequest.criptoQuantity(),
                transactionRequest.criptoQuote(),
                transactionRequest.type()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.mapperToDTO(serviceTransaction.saveTransaction(transaction)));
    }


    @Operation(summary = "Get all actives transactions", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = TransactionDTO.class)))),
            @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/active")
    public ResponseEntity<List<TransactionDTO>> allTransactionActive() {
        List<Transaction> transactions = serviceTransaction.findAllTransactionsByStateProcess(StateProcess.ACTIVE);

        return ResponseEntity.ok().body(this.mapperToListDTO(transactions));
    }


    @PutMapping("/process_intention/{id}")
    public ResponseEntity<Transaction> processTransaction(HttpServletRequest request, @PathVariable Long id) {
        UserModel user = authService.getUserToken(request);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.processIntentionTo(transaction);

        return ResponseEntity.ok().body(serviceTransaction.updateTransaction(id, transaction));
    }


    @PutMapping("/make_transfer/{id}")
    public ResponseEntity<Transaction> makeTransfer(HttpServletRequest request, @PathVariable Long id) {
        UserModel user = authService.getUserToken(request);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.makeTransfer(transaction);

        return ResponseEntity.ok().body(serviceTransaction.updateTransaction(id, transaction));
    }


    @PutMapping("/confirm_reception/{id}")
    public ResponseEntity<Transaction> confirmTransaction(HttpServletRequest request, @PathVariable Long id) {
        UserModel user = authService.getUserToken(request);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.confirmReception(transaction);

        return ResponseEntity.ok().body(serviceTransaction.updateTransaction(id, transaction));
    }

    private TransactionDTO mapperToDTO(Transaction transaction) {
        return new TransactionDTO(
                transaction.getId(),
                transaction.getDateTime(),
                transaction.getCripto().getSymbol(),
                transaction.getCriptoQuantity(),
                transaction.getCriptoQuote(),
                transaction.getArgentineCurrency(),
                transaction.getUser().getUsername(),
                transaction.getUserTransaction().getUsername(),
                transaction.getType(),
                transaction.getStateProcess());
    }

    private List<TransactionDTO> mapperToListDTO(List<Transaction> transactions) {
        List<TransactionDTO> transactionDTOs = new ArrayList<>();

        for (Transaction transaction: transactions) {
            transactionDTOs.add(this.mapperToDTO(transaction));
        }

        return transactionDTOs;
    }
    
}

package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.*;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.TransactionDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.TransactionRequestDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.CriptoService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService serviceTransaction;

    @Autowired
    private CriptoService serviceCripto;

    @Autowired
    private UserModelService serviceUserModel;

    @Autowired
    private AuthenticationService authService;

    private final TransactionDTO transactionDTO = new TransactionDTO();

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = serviceTransaction.findAllTransactions();

        return ResponseEntity.ok().body(transactionDTO.mapperToListDTO(transactions));
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> saveTransaction(HttpServletRequest request, @RequestBody TransactionRequestDTO transactionRequest) {

        UserModel user = authService.getUserToken(request);
        Cripto cripto = serviceCripto.getCriptoBySymbol(transactionRequest.getCripto());

        Transaction transaction = user.createTransaction(
                cripto,
                transactionRequest.getCriptoQuantity(),
                transactionRequest.getCriptoQuote(),
                transactionRequest.getType()
        );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(transactionDTO.mapperToDTO(serviceTransaction.saveTransaction(transaction)));
    }

    @GetMapping("/active")
    public ResponseEntity<List<TransactionDTO>> allTransactionActive() {
        List<Transaction> transactions = serviceTransaction.findAllTransactionsByStateProcess(StateProcess.ACTIVE);

        return ResponseEntity.ok().body(transactionDTO.mapperToListDTO(transactions));
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
    
}

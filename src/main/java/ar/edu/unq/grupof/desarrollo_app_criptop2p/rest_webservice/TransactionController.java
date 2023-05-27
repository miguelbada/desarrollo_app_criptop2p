package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.*;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.TransactionDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.TransactionRequestDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.CriptoService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {
    @Autowired
    TransactionService serviceTransaction;

    @Autowired
    CriptoService serviceCripto;

    @Autowired
    UserModelService serviceUserModel;

    private final ModelMapper mapper = new ModelMapper();
    private final TransactionDTO transactionDTO = new TransactionDTO();

    @GetMapping("/all")
    public ResponseEntity<List<TransactionDTO>> getAllTransactions() {
        List<Transaction> transactions = serviceTransaction.findAllTransactions();

        return ResponseEntity.ok().body(transactionDTO.mapperToListDTO(transactions));
    }

    @PostMapping("/create")
    public ResponseEntity<TransactionDTO> saveTransaction(@RequestBody TransactionRequestDTO request) {

        UserModel user = serviceUserModel.findUserModelByUsername(request.getUser());
        Cripto cripto = serviceCripto.getCriptoBySymbol(request.getCripto());

        Transaction transaction = user.createTransaction(
                cripto,
                request.getCriptoQuantity(),
                request.getCriptoQuote(),
                request.getType()
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(transactionDTO.mapperToDTO(serviceTransaction.saveTransaction(transaction)));
    }

    @GetMapping("/active")
    public ResponseEntity<List<TransactionDTO>> allTransactionActive() {
        List<Transaction> transactions = serviceTransaction.findAllTransactionsByStateProcess(StateProcess.ACTIVE);

        return ResponseEntity.ok().body(transactionDTO.mapperToListDTO(transactions));
    }

    @PutMapping("/process_intention/{id}/user/{user_id}")
    public ResponseEntity<Transaction> processTransaction(@PathVariable Long id, @PathVariable Long user_id) {
        UserModel user = serviceUserModel.getUserModelById(user_id);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.processIntentionTo(transaction);

        return ResponseEntity.ok().body(serviceTransaction.saveTransaction(transaction));
    }

    @PutMapping("/make_transfer/{id}/user/{user_id}")
    public ResponseEntity<Transaction> makeTransfer(@PathVariable Long id, @PathVariable Long user_id) {
        UserModel user = serviceUserModel.getUserModelById(user_id);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.makeTransfer(transaction);

        return ResponseEntity.ok().body(serviceTransaction.saveTransaction(transaction));
    }

    @PutMapping("/confirm_reception/{id}/user/{user_id}")
    public ResponseEntity<Transaction> confirmTransaction(@PathVariable Long id, @PathVariable Long user_id) {
        UserModel user = serviceUserModel.getUserModelById(user_id);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.confirmReception(transaction);

        return ResponseEntity.ok().body(serviceTransaction.saveTransaction(transaction));
    }
    
}

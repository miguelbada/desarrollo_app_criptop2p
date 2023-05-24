package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.*;
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

    @GetMapping("/all")
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        return ResponseEntity.ok(serviceTransaction.findAllTransactions());
    }

    @PostMapping("/create/{id}/{simbol}/{criptoQuantity}/{criptoQuote}/{argentineCurrency}/{type}")
    public ResponseEntity<Transaction> saveTransaction(@PathVariable Long id,
                                                       @PathVariable String simbol,
                                                       @PathVariable Double criptoQuantity,
                                                       @PathVariable Double criptoQuote,
                                                       @PathVariable Double argentineCurrency,
                                                       @PathVariable OperationType type) {

        Cripto cripto = serviceCripto.getCriptoBySymbol(simbol);
        UserModel user = serviceUserModel.getUserModelById(id);
        Transaction transaction = user.createTransaction(cripto, criptoQuantity, criptoQuote, argentineCurrency, type);

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceTransaction.saveTransaction(transaction));
    }

    @GetMapping("/active")
    public ResponseEntity<List<Transaction>> allTransactionActive() {
        return ResponseEntity.ok().body(serviceTransaction.findAllTransactionsByStateProcess(StateProcess.ACTIVE));
    }

    @PutMapping("/process_intention/{id}/{user_id}")
    public ResponseEntity<Transaction> processTransaction(@PathVariable Long id, @PathVariable Long user_id) {
        UserModel user = serviceUserModel.getUserModelById(user_id);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.processIntentionTo(transaction);

        return ResponseEntity.ok().body(serviceTransaction.saveTransaction(transaction));
    }

    @PutMapping("/make_transfer/{id}/{user_id}")
    public ResponseEntity<Transaction> makeTransfer(@PathVariable Long id, @PathVariable Long user_id) {
        UserModel user = serviceUserModel.getUserModelById(user_id);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.makeTransfer(transaction);

        return ResponseEntity.ok().body(serviceTransaction.saveTransaction(transaction));
    }

    @PutMapping("/confirm_reception/{id}/{user_id}")
    public ResponseEntity<Transaction> confirmTransaction(@PathVariable Long id, @PathVariable Long user_id) {
        UserModel user = serviceUserModel.getUserModelById(user_id);
        Transaction transaction = serviceTransaction.getTransactionById(id);

        user.confirmReception(transaction);

        return ResponseEntity.ok().body(serviceTransaction.saveTransaction(transaction));
    }
    
}

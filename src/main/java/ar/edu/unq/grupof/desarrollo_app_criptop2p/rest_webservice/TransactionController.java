package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.OperationType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.CriptoService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
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
    public ResponseEntity<Transaction> saveTransaction(@PathVariable Integer id,
                                                       @PathVariable String simbol,
                                                       @PathVariable Double criptoQuantity,
                                                       @PathVariable Double criptoQuote,
                                                       @PathVariable Double argentineCurrency,
                                                       @PathVariable OperationType type) {

        Cripto cripto = serviceCripto.getCriptoBySymbol(simbol);
        UserModel user = serviceUserModel.getUserModelByid(id);
        Transaction transaction = user.createTransaction(cripto, criptoQuantity, criptoQuote, argentineCurrency, type);

        return ResponseEntity.status(HttpStatus.CREATED).body(serviceTransaction.saveTransaction(transaction));
    }

}

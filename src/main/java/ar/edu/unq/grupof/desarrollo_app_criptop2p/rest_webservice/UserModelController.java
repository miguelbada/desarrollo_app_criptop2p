package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.OperationVolumenReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserModelDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserModelController {
    @Autowired
    private UserModelService service;

    @Autowired
    private TransactionService transactionService;
    private final ModelMapper mapper = new ModelMapper();

    @GetMapping("/all")
    public ResponseEntity<List<UserModel>> getAllUserModel() {
        return ResponseEntity.ok().body(service.findAllUser());
    }

    @PostMapping("/sign_up")
    public ResponseEntity<UserModel> userSignUp(@RequestBody @Valid UserModelDTO newUser) {
        UserModel user = mapper.map(newUser, UserModel.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUserModel(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getUserModelById(id));
    }

    @GetMapping("/{user_id}/{start_date}/{end_date}")
    public ResponseEntity<OperationVolumenReport> operationVolumenReport(
            @PathVariable Long user_id,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start_date,
            @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end_date) {

        return ResponseEntity.ok().body(transactionService.findTransactionBetween(user_id, start_date, end_date));
    }

}

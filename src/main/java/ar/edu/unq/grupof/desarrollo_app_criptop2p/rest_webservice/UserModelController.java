package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.OperationVolumenReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserModelDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "user")
public class UserModelController {

    @Autowired
    private UserModelService service;

    @Autowired
    private TransactionService transactionService;
    private final UserModelDTO userDTO = new UserModelDTO();

    @Operation(summary = "Get all users", responses = {
                    @ApiResponse(description = "Susses", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403")})
    @GetMapping("/all")
    public ResponseEntity<List<UserModelDTO>> getAllUserModel() {
        List<UserModel> users = service.findAllUser();

        return ResponseEntity.ok().body(userDTO.mapperToListDTO(users));
    }



    @Operation(summary = "Get a user by id", responses = {
                    @ApiResponse(description = "Susses", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403")})
    @GetMapping("/{id}")
    public ResponseEntity<UserModel> findUserById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getUserModelById(id));
    }



    @Operation(summary = "shows the trading volume of a user between two dates", responses = {
                    @ApiResponse(description = "Susses", responseCode = "200"),
                    @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403")})
    @PostMapping("/user_report")
    public ResponseEntity<OperationVolumenReport> operationVolumenReport(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) UserReport userReport) {
        return ResponseEntity.ok().body(transactionService.findTransactionBetween(userReport));
    }

}

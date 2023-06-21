package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.OperationVolumenReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserModelDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TransactionService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/user")
@Tag(name = "User")
public class UserModelController {

    @Autowired
    private UserModelService service;

    @Autowired
    private TransactionService transactionService;
    private final UserModelDTO userDTO = new UserModelDTO();

    @Operation(summary = "Get all users", responses = {
                    @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserModelDTO.class)))),
                    @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
                    @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/all")
    public ResponseEntity<List<UserModelDTO>> getAllUserModel() {
        List<UserModel> users = service.findAllUser();

        return ResponseEntity.ok().body(userDTO.mapperToListDTO(users));
    }



    @Operation(summary = "Get a user by id", responses = {
                    @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserModelDTO.class))),
                    @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
                    @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/{id}")
    public ResponseEntity<UserModelDTO> findUserById(@PathVariable @Parameter(description = "The Id of the user to find") Long id) {
        UserModelDTO user = userDTO.mapperToDTO(service.getUserModelById(id));

        return ResponseEntity.ok().body(user);
    }



    @Operation(summary = "shows the trading volume of a user between two dates", responses = {
                    @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationVolumenReport.class))),
                    @ApiResponse(description = "Unauthorized or Invalid Token", responseCode = "403"),
                    @ApiResponse(description = "Not found", responseCode = "404"),
                    @ApiResponse(description = "Internal error", responseCode = "500")})
    @PostMapping("/user_report")
    public ResponseEntity<OperationVolumenReport> operationVolumenReport(@RequestBody @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) UserReport userReport) {
        return ResponseEntity.ok().body(transactionService.findTransactionBetween(userReport));
    }

}

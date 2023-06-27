package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.AuthenticationRequest;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.AuthenticationResponse;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserModelDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authenticate")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @Operation(summary = "Register a new User", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UserModelDTO newUser) {
        return ResponseEntity.ok(service.register(newUser));
    }

    @Operation(summary = "Login a user, if succes, return Token", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "application/json", schema = @Schema(implementation = AuthenticationResponse.class))),
            @ApiResponse(description = "Unauthorized or Invalid password/username", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }


    @Operation(summary = "Test. Return name of the login user", responses = {
            @ApiResponse(description = "Succes", responseCode = "200", content = @Content(mediaType = "text/plain", schema = @Schema(type = "string"))),
            @ApiResponse(description = "Unauthorized or Invalid password/username", responseCode = "403"),
            @ApiResponse(description = "Not found", responseCode = "404"),
            @ApiResponse(description = "Internal error", responseCode = "500")})
    @GetMapping("/currentusername")
    public String currentUserName(HttpServletRequest request) {
        return service.getUserToken(request).getName();
    }
}

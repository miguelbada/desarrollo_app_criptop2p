package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.AuthenticationRequest;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.AuthenticationResponse;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.RegisterRequest;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserModelDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.Principal;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private AuthenticationService service;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody @Valid UserModelDTO newUser) {
        return ResponseEntity.ok(service.register(newUser));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/refresh-token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        service.refreshToken(request, response);
    }

   /* @GetMapping("/currentusername")
    public String currentUserName(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        return authentication.getName();
    }*/

    @GetMapping("/currentusername")
    public String currentUserName(HttpServletRequest request) {

        return service.getUserToken(request).getName();
    }
}

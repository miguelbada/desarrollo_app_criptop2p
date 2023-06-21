package ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.RoleType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Token;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.TokenType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.UserNotFoundException;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.AuthenticationRequest;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.AuthenticationResponse;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserModelDTO;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.RoleService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.TokenService;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserModelService userService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(UserModelDTO request) {
        UserModel user = request.mapperToEntity();
        user.addRole(roleService.findByRoleType(RoleType.USER));
        UserModel savedUser = userService.saveUserModel(user);
        String jwtToken = jwtService.generateToken(user);

        saveUserToken(savedUser, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()));

        UserModel user = userService.findUserModelByEmail(request.getEmail()).orElseThrow(() -> new UserNotFoundException("User not found"));

            String jwtToken = jwtService.generateToken(user);

            revokeAllUserTokens(user);
            saveUserToken(user, jwtToken);

            return AuthenticationResponse.builder()
                    .accessToken(jwtToken)
                    .build();
    }

    private void saveUserToken(UserModel user, String jwtToken) {
        Token token = Token.builder()
                .user(user)
                .token(jwtToken)
                .type(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenService.saveToken(token);
    }

    private void revokeAllUserTokens(UserModel user) {
        var validUserTokens = tokenService.findAllValidTokenByUser(user.getId());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenService.saveAll(validUserTokens);
    }

    public UserModel getUserToken(HttpServletRequest request) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String accsessToken;
        final String userEmail;

        if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
            return null;
        }

        accsessToken = authHeader.substring(7);
        userEmail = jwtService.extractUsername(accsessToken);

        if (userEmail != null) {
            UserModel user = this.userService.findUserModelByEmail(userEmail)
                    .orElseThrow();
            if (jwtService.isTokenValid(accsessToken, user)) {
                return user;
            }
        }
        return null;
    }

}

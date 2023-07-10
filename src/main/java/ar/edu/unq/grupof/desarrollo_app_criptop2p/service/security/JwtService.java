package ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String extractUsername(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsResolver);
    String generateToken(UserDetails userDetails);
    String generateToken(Map<String, Object> extraClaims, UserDetails userDetails);
    String buildToken(Map<String, Object> extraClaims, UserDetails userDetails, long expiration);
    boolean isTokenValid(String token, UserDetails userDetails);
    boolean isTokenExpired(String token);
}

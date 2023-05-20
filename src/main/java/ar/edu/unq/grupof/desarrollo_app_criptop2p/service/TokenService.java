package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Token;

import java.util.List;

public interface TokenService {
    Token saveToken(Token token);
    List<Token> findAllValidTokenByUser(Long id);
    void saveAll(List<Token> validUserTokens);
}

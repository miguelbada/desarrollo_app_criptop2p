package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Token;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TokenServiceImpl implements TokenService{
    @Autowired
    private TokenRepository repository;

    @Override
    public Token saveToken(Token token) {
        return repository.save(token);
    }

    @Override
    public List<Token> findAllValidTokenByUser(Long id) {
        return repository.findAllValidTokenByUser(id);
    }

    @Override
    public void saveAll(List<Token> validUserTokens) {
        repository.saveAll(validUserTokens);
    }
}

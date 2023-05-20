package ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {
    Optional<Token> findByToken(String token);
    @Query(value = """
      select t from Token t inner join UserModel u\s
      on t.user.id = u.id\s
      where u.id = :id and (t.expired = false or t.revoked = false)\s
      """)
    List<Token> findAllValidTokenByUser(Long id);
}

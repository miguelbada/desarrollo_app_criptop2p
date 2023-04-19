package ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepository extends JpaRepository<User, String> {
}

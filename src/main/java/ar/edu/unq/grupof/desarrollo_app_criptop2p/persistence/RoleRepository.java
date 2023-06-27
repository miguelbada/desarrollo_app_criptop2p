package ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Role;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleById(Long id);
    Optional<Role> findRoleByRoleType(RoleType type);

}

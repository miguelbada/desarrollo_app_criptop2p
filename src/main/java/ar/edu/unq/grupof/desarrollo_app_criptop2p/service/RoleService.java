package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Role;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.RoleType;

import java.util.List;
import java.util.Optional;

public interface RoleService {
    List<Role> findAllRoles();
    Optional<Role> findRoleById(Long id);
    Role saveRole(Role role);
    Role findByRoleType(RoleType type);
}

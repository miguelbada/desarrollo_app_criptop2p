package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Role;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.RoleType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.RoleNotFoundException;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{
    @Autowired
    RoleRepository repository;

    @Override
    public List<Role> findAllRoles() {
        return repository.findAll();
    }

    @Override
    public Optional<Role> findRoleById(Long id) {
        return repository.findRoleById(id);
    }

    @Override
    public Role saveRole(Role role) {
        return repository.save(role);
    }

    @Override
    public Role findByRoleType(RoleType type) {

        return repository.findRoleByRoleType(type).orElseThrow(() -> new RoleNotFoundException("Role " + type + " Not found"));
    }
}

package ar.edu.unq.grupof.desarrollo_app_criptop2p.service.security;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserModelService service;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel usuario = service.findUserModelByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username));

        return new User(
                usuario.getUsername(),
                usuario.getPassword(),
                true,
                true,
                true,
                true,
                usuario.getAuthorities()
        );
    }
}

package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.UserNotFoundException;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserModelServiceImpl implements UserModelService{
    @Autowired
    private UserModelRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public List<UserModel> findAllUser() {
        return repository.findAll();
    }

    @Override
    public Optional<UserModel> findUserModelByUsername(String username) {
        return Optional.ofNullable(repository.findUserModelByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not foung by username " + username)));
    }

    @Override
    public Optional<UserModel> findUserModelByEmail(String email) {
        return Optional.ofNullable(repository.findUserModelByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not foung by email " + email)));
    }

    @Override
    public UserModel saveUserModel(UserModel userModel) {
        userModel.setPassword(encoder.encode(userModel.getPassword()));

        return repository.save(userModel);
    }

    @Override
    public UserModel getUserModelById(Long id) {
        return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
    }

    @Override
    public void deleteUserModel(Long id) {
        repository.deleteById(id);
    }

}

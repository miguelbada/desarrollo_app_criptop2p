package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserModelServiceImpl implements UserModelService{
    @Autowired
    private UserModelRepository repository;

    @Override
    public List<UserModel> findAllUser() {
        return repository.findAll();
    }

    @Override
    public UserModel saveUserModel(UserModel userModel) {
        return repository.save(userModel);
    }

    @Override
    public UserModel getUserModelByid(Integer id) {
        return repository.findById(id).orElseThrow(() -> new Error("User not found by id: " + id));
        //return repository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by id: " + id));
    }

    @Override
    public void deleteUserModel(Integer id) {
        repository.deleteById(id);
    }
}

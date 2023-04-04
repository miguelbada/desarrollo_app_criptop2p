package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

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
    public void deleteUserModel(String criptoWallet) {
        repository.deleteById(criptoWallet);
    }
}

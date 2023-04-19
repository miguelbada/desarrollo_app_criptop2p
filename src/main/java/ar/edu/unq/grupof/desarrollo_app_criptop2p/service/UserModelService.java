package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.User;

import java.util.List;

public interface UserModelService {
    List<User> findAllUser();
    User saveUserModel(User userModel);
    void deleteUserModel(String criptoWallet);
}

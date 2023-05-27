package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;

import java.util.List;
import java.util.Optional;

public interface UserModelService {
    List<UserModel> findAllUser();

    UserModel findUserModelByUsername(String username);
    Optional<UserModel> findUserModelByEmail(String email);
    UserModel saveUserModel(UserModel userModel);
    UserModel getUserModelById(Long id);
    void deleteUserModel(Long id);
}

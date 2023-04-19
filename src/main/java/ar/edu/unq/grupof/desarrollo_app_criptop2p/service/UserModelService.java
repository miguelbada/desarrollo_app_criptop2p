package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;

import java.util.List;

public interface UserModelService {
    List<UserModel> findAllUser();
    UserModel saveUserModel(UserModel userModel);
    void deleteUserModel(Integer id);
}

package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.service.UserModelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserModelController {
    @Autowired
    private UserModelService service;

    @GetMapping("/users")
    public ResponseEntity<List<UserModel>> getAllUserModel() {
        return ResponseEntity.ok().body(service.findAllUser());
    }

    @PostMapping("/SignUp")
    public ResponseEntity<UserModel> userSignUp(@Valid @RequestBody UserModel userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUserModel(userModel));
    }
}

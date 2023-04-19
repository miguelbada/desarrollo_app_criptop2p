package ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.User;
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
    public ResponseEntity<List<User>> getAllUserModel() {
        return ResponseEntity.ok().body(service.findAllUser());
    }

    @PostMapping("/SignUp")
    public ResponseEntity<User> userSignUp(@Valid @RequestBody User userModel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveUserModel(userModel));
    }
}

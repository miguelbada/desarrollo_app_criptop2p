package ar.edu.unq.grupof.desarrollo_app_criptop2p.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserModelTest {

    @Test
    void constructorUserModelTest() {
        UserModel userModel = UserModel.builder().name("Miguel").build();

        assertEquals(userModel.getName(),"Miguel");
    }
}

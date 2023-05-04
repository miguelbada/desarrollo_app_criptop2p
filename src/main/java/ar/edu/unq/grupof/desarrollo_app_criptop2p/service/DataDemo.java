package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.ArrayList;
import java.util.List;

@Builder
@AllArgsConstructor
public class DataDemo {
    List<UserModel> usuarios;

    public DataDemo() {
        this.usuarios = new ArrayList<>();
    }

    public List<UserModel> getUsers() {
        UserModel miguel = UserModel.builder()
                .name("Miguel")
                .lastName("Bada")
                .email("miguel@gmail.com")
                .address("Monroe 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijklm")
                .cryptoWallet("12345678")
                .doneOperations(0)
                .reputation(0)
                .build();

        UserModel nancy = UserModel.builder()
                .name("Nancy")
                .lastName("Nagel")
                .email("nancy@gmail.com")
                .address("Monroe 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijkln")
                .cryptoWallet("12345679")
                .doneOperations(0)
                .reputation(0)
                .build();

        UserModel juan = UserModel.builder()
                .name("Juan")
                .lastName("Gomez")
                .email("juan@gmail.com")
                .address("Rivadavia 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijkln")
                .cryptoWallet("12345679")
                .doneOperations(0)
                .reputation(0)
                .build();

        this.usuarios.add(miguel);
        this.usuarios.add(nancy);
        this.usuarios.add(juan);

        return usuarios;
    }

}

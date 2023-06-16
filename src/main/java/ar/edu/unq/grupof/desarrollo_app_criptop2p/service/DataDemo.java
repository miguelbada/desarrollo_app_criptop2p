package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Builder
@AllArgsConstructor
public class DataDemo {
    List<UserModel> users;

    public DataDemo() {
        this.users = new ArrayList<>();
    }

    public List<UserModel> getUsers() {
        UserModel miguel = UserModel.builder()
                .roles(new ArrayList<>())
                .name("Miguel")
                .lastName("Bada")
                .username("miguel@gmail.com")
                .email("miguel@gmail.com")
                .address("Monroe 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijklm")
                .cryptoWallet("12345678")
                .doneOperations(0)
                .reputation(0)
                .build();

        UserModel nancy = UserModel.builder()
                .roles(new ArrayList<>())
                .name("Nancy")
                .lastName("Nagel")
                .username("nancy@gmail.com")
                .email("nancy@gmail.com")
                .address("Rivadavia 1234")
                .password("123456/aC")
                .cvuMercadoPago("123456789abcdefghijkln")
                .cryptoWallet("12345680")
                .doneOperations(0)
                .reputation(0)
                .build();

        UserModel martin = UserModel.builder()
                .roles(new ArrayList<>())
                .name("Martin")
                .lastName("Perez")
                .username("martin@gmail.com")
                .email("martin@gmail.com")
                .address("Marmol 1234")
                .password("123456/aD")
                .cvuMercadoPago("123456789abcdefghijkln")
                .cryptoWallet("12345679")
                .doneOperations(0)
                .reputation(0)
                .build();

        this.users.add(miguel);
        this.users.add(nancy);
        this.users.add(martin);

        return users;
    }

    public void setUsers(List<UserModel> users) {
        this.users = users;
    }

}

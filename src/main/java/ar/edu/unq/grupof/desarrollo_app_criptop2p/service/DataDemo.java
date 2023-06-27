package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataDemo {
    List<UserModel> users;
    List<Cripto> criptos;
    Double argentineCurrency;

    public DataDemo() {
        this.users = new ArrayList<>();
        this.criptos = new ArrayList<>();
        this.argentineCurrency = 298.05;
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

    public List<Cripto> getCriptos() {
        Cripto btcusdt = Cripto
                .builder()
                .symbol("BTCUSDT")
                .dateTime(LocalDateTime.now())
                .price(30736.13)
                .argentineCurrency(7758142.042794021)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto ethusdt = Cripto
                .builder()
                .symbol("ETHUSDT")
                .dateTime(LocalDateTime.now())
                .price(1894.38)
                .argentineCurrency(478162.64191452006)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto bnbusdt = Cripto
                .builder()
                .symbol("BNBUSDT")
                .dateTime(LocalDateTime.now())
                .price(245.3)
                .argentineCurrency(61916.456076200004)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto neousdt = Cripto
                .builder()
                .symbol("NEOUSDT")
                .dateTime(LocalDateTime.now())
                .price(9.21)
                .argentineCurrency(2324.70672834)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto adausdt = Cripto
                .builder()
                .symbol("ADAUSDT")
                .dateTime(LocalDateTime.now())
                .price(0.2965)
                .argentineCurrency(74.839907161)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto trxusdt = Cripto
                .builder()
                .symbol("TRXUSDT")
                .dateTime(LocalDateTime.now())
                .price(0.07306)
                .argentineCurrency(18.441158911240002)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto maticusdt = Cripto
                .builder()
                .symbol("MATICUSDT")
                .dateTime(LocalDateTime.now())
                .price(0.6773)
                .argentineCurrency(170.95807460420002)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto atomusdt = Cripto
                .builder()
                .symbol("ATOMUSDT")
                .dateTime(LocalDateTime.now())
                .price(9.332)
                .argentineCurrency(2355.5008891280004)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto dotusdt = Cripto
                .builder()
                .symbol("DOTUSDT")
                .dateTime(LocalDateTime.now())
                .price(5.082)
                .argentineCurrency(1282.753484628)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto aaveusdt = Cripto
                .builder()
                .symbol("AAVEUSDT")
                .dateTime(LocalDateTime.now())
                .price(57.04)
                .argentineCurrency(14397.53222416)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto audiousdt = Cripto
                .builder()
                .symbol("AUDIOUSDT")
                .dateTime(LocalDateTime.now())
                .price(0.1921)
                .argentineCurrency(48.4881826834)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto axsusdt = Cripto
                .builder()
                .symbol("AXSUSDT")
                .dateTime(LocalDateTime.now())
                .price(5.68)
                .argentineCurrency(1433.69535472)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto cakeusdt = Cripto
                .builder()
                .symbol("CAKEUSDT")
                .dateTime(LocalDateTime.now())
                .price(1.44)
                .argentineCurrency(363.47206176)
                .historicalPrices(new ArrayList<>())
                .build();

        Cripto aliceusdt = Cripto
                .builder()
                .symbol("ALICEUSDT")
                .dateTime(LocalDateTime.now())
                .price(1.03)
                .argentineCurrency(259.98348862)
                .historicalPrices(new ArrayList<>())
                .build();

        this.criptos.addAll(Arrays.asList(
                btcusdt,
                ethusdt,
                bnbusdt,
                neousdt,
                adausdt,
                trxusdt,
                maticusdt,
                atomusdt,
                dotusdt,
                aaveusdt,
                audiousdt,
                axsusdt,
                cakeusdt,
                aliceusdt
        ));

        return criptos;
    }

    public Double getArgentineCurrency() {
        return argentineCurrency;
    }

}

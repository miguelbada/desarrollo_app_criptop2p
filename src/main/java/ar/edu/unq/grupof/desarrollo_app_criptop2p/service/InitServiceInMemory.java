package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class InitServiceInMemory {
    protected final Log logger = LogFactory.getLog(getClass());

    @Value("${spring.datasource.driverClassName:NONE}")
    private String className;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private CriptoService criptoService;

    @PostConstruct
    public void initialize() {
        if (className.equals("org.h2.Driver")) {
            logger.info("Init Data Using H2 DB");
            fireInitialData();
        }
    }

    private void fireInitialData() {
        loadUsers();


    }

    private void loadUsers() {
        UserModel miguel = UserModel.builder()
                .name("Miguel")
                .lastName("Bada")
                .email("miguel@gmail.com")
                .address("monroe1234")
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
                .address("monroe1234")
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

        userModelService.saveUserModel(miguel);
        userModelService.saveUserModel(nancy);
        userModelService.saveUserModel(juan);

        List<Cripto> criptos = criptoService.getBinanceCryptos();

        criptos.forEach(cripto -> criptoService.saveCripto(cripto));

    }
}

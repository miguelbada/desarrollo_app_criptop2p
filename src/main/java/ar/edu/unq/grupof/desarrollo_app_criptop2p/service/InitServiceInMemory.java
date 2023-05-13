package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Cripto;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Role;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.RoleType;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.UserModel;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Arrays;
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

    @Autowired
    private RoleService roleService;

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
        Role roleAdmin = Role.builder().roleType(RoleType.ADMIN).build();
        Role roleUser = Role.builder().roleType(RoleType.USER).build();

        UserModel miguel = UserModel.builder()
                .roles(Arrays.asList(roleAdmin))
                .name("Miguel")
                .lastName("Bada")
                .username("migue")
                .email("miguel@gmail.com")
                .address("Monroe 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijklm")
                .cryptoWallet("12345678")
                .doneOperations(0)
                .reputation(0)
                .build();

        UserModel martin = UserModel.builder()
                .roles(Arrays.asList(roleUser))
                .name("Martin")
                .lastName("Perez")
                .username("martin")
                .email("martin@gmail.com")
                .address("Marmol 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijkln")
                .cryptoWallet("12345679")
                .doneOperations(0)
                .reputation(0)
                .build();

        UserModel juan = UserModel.builder()
                .roles(Arrays.asList(roleUser))
                .name("Juan")
                .lastName("Gomez")
                .username("juanjo")
                .email("juan@gmail.com")
                .address("Rivadavia 1234")
                .password("123456/aB")
                .cvuMercadoPago("123456789abcdefghijkln")
                .cryptoWallet("12345680")
                .doneOperations(0)
                .reputation(0)
                .build();

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        userModelService.saveUserModel(miguel);
        userModelService.saveUserModel(martin);
        userModelService.saveUserModel(juan);

        List<Cripto> criptos = criptoService.getBinanceCryptos();
        criptos.forEach(cripto -> criptoService.saveCripto(cripto));

    }
}

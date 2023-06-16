package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.*;
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

    private final DataDemo data = new DataDemo();

    @Value("${spring.datasource.driverClassName:NONE}")
    private String className;

    @Autowired
    private UserModelService userModelService;

    @Autowired
    private CriptoService criptoService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private TransactionService transactionService;

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

        UserModel miguel = data.getUsers().get(0);
        miguel.addRole(roleAdmin);

        UserModel nancy = data.getUsers().get(1);
        nancy.addRole(roleUser);

        UserModel martin = data.getUsers().get(2);
        martin.addRole(roleUser);

        roleService.saveRole(roleAdmin);
        roleService.saveRole(roleUser);
        userModelService.saveUserModel(miguel);
        userModelService.saveUserModel(nancy);
        userModelService.saveUserModel(martin);

        List<Cripto> criptos = criptoService.getBinanceCryptos();
        criptos.forEach(cripto -> criptoService.saveCripto(cripto));

        Transaction transactionMartin1 = martin.createTransaction(criptos.get(0),1.0, criptos.get(0).getPrice(), OperationType.BUY);
        Transaction transactionMartin2 = martin.createTransaction(criptos.get(1), 2.0, criptos.get(1).getPrice(), OperationType.BUY);
        Transaction transactionMartin3 = martin.createTransaction(criptos.get(0),1.5, criptos.get(0).getPrice(), OperationType.BUY);
        Transaction transactionNancy = nancy.createTransaction(criptos.get(3), 3.0, criptos.get(3).getPrice(), OperationType.SALE);

        Transaction transactionMartin1Save = transactionService.saveTransaction(transactionMartin1);
        Transaction transactionMartin2Save = transactionService.saveTransaction(transactionMartin2);
        Transaction transactionMartin3Save = transactionService.saveTransaction(transactionMartin3);
        transactionService.saveTransaction(transactionNancy);

        miguel.processIntentionTo(transactionMartin1);
        martin.makeTransfer(transactionMartin1);
        miguel.confirmReception(transactionMartin1);

        nancy.processIntentionTo(transactionMartin2);
        martin.makeTransfer(transactionMartin2);
        nancy.confirmReception(transactionMartin2);

        miguel.processIntentionTo(transactionMartin3);
        martin.makeTransfer(transactionMartin3);
        miguel.confirmReception(transactionMartin3);


        transactionService.updateTransaction(transactionMartin1Save.getId(), transactionMartin1);
        transactionService.updateTransaction(transactionMartin2Save.getId(), transactionMartin2);
        transactionService.updateTransaction(transactionMartin3Save.getId(), transactionMartin3);
    }
}

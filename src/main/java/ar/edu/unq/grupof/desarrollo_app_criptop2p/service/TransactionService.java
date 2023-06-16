package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.OperationVolumenReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.StateProcess;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.UserReport;
import java.util.List;

public interface TransactionService {
    List<Transaction> findAllTransactions();
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
    List<Transaction> findAllTransactionsByStateProcess(StateProcess process);
    Transaction updateTransaction(Long TransactionId, Transaction transaction);
    OperationVolumenReport findTransactionBetween(UserReport userReport);
}

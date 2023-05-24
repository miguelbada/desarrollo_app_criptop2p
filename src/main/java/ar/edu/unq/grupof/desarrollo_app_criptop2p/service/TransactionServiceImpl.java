package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.rest_webservice.dto.OperationVolumenReport;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.StateProcess;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.TransactionNotFoundException;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.TransactionRepository;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.UserModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserModelRepository userModelRepository;

    @Override
    public List<Transaction> findAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found by id: " + id));
    }

    @Override
    public List<Transaction> findAllTransactionsByStateProcess(StateProcess process) {
        return transactionRepository.findTransactionByStateProcess(process);
    }

    @Override
    public Transaction updateTransaction(Long transactionId, Transaction newTransaction) {
        Transaction transaction = transactionRepository.findById(transactionId).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        transaction.setUserTransaction(newTransaction.getUserTransaction());
        transaction.setStateProcess(newTransaction.getStateProcess());

        userModelRepository.save(newTransaction.getUser());
        userModelRepository.save(newTransaction.getUserTransaction());

        return transactionRepository.save(transaction);
    }

    @Override
    public OperationVolumenReport findTransactionBetween(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        List<Transaction> transactions = transactionRepository.findTransactionByDateTimeBetweenAndStateProcess(startDate, endDate, StateProcess.PROCESSED);
        OperationVolumenReport report = new OperationVolumenReport();

        List<Transaction> transactionsByUser = transactions.stream().filter(transaction ->
                Objects.equals(transaction.getUser().getId(), id)
                        || Objects.equals(transaction.getUserTransaction().getId(), id)).toList();

        for (Transaction transaction : transactionsByUser) {
                report.addPrice(transaction.getCriptoQuote());
                report.addArgentinePesos(transaction.getArgentineCurrency());
                report.addCripto(transaction);
        }

        return report;
    }

}

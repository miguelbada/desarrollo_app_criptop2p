package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.StateProcess;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.exception.TransactionNotFoundException;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository repository;

    @Override
    public List<Transaction> findAllTransactions() {
        return repository.findAll();
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return repository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found by id: " + id));
    }

    @Override
    public List<Transaction> findAllTransactionsByStateProcess(StateProcess process) {
        return repository.findTransactionByStateProcess(process);
    }

}

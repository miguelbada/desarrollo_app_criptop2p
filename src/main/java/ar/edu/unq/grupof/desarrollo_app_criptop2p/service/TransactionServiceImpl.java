package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Transaction getTransactionById(Integer id) {

        return repository.findById(id).orElseThrow(() -> new Error("Transaction not found by id: " + id));
    }


}

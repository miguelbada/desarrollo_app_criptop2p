package ar.edu.unq.grupof.desarrollo_app_criptop2p.service;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findAllTransactions();
    Transaction saveTransaction(Transaction transaction);
    Transaction getTransactionById(Long id);
}

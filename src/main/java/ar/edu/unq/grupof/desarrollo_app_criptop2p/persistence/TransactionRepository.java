package ar.edu.unq.grupof.desarrollo_app_criptop2p.persistence;

import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.StateProcess;
import ar.edu.unq.grupof.desarrollo_app_criptop2p.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionByStateProcess(StateProcess process);
    List<Transaction> findTransactionByDateTimeBetweenAndStateProcess(LocalDateTime dateTime,
                                                                      LocalDateTime dateTime2,
                                                                      StateProcess stateProcess);
}

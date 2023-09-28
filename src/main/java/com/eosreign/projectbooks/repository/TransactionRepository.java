package com.eosreign.projectbooks.repository;

import com.eosreign.projectbooks.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Optional<List<Transaction>> findTransactionsByClient_Id(long id);
    Optional<List<Transaction>> findTransactionsByBook_Id(long id);
    Boolean existsTransactionsByBook_Id(long id);
    Boolean existsTransactionsByClient_Id(long id);

}

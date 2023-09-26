package com.eosreign.projectbooks.repository;

import com.eosreign.projectbooks.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findTransactionsByClient_Id(long id);
    List<Transaction> findTransactionsByBook_Id(long id);
}

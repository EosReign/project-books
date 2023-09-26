package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.dto.TransactionsDTO;

public interface TransactionServiceImpl {
    TransactionDTO createTransaction(TransactionDTO dto);
    TransactionDTO readTransaction(long id);
    TransactionsDTO readTransactionsByClientId(long id);
    TransactionsDTO readTransactionsByBookId(long id);
    TransactionDTO updateTransaction(TransactionDTO dto, long id);
    void deleteTransaction(long id);
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.dto.TransactionsDTO;
import com.eosreign.projectbooks.entity.Transaction;
import com.eosreign.projectbooks.exception.BookNotFoundException;
import com.eosreign.projectbooks.exception.ClientNotFoundException;
import com.eosreign.projectbooks.exception.TransactionNotFoundException;
import com.eosreign.projectbooks.mapper.TransactionMapper;
import com.eosreign.projectbooks.mapper.TransactionsMapper;
import com.eosreign.projectbooks.repository.BookRepository;
import com.eosreign.projectbooks.repository.ClientRepository;
import com.eosreign.projectbooks.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService implements TransactionServiceImpl {
    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private TransactionService(TransactionRepository transactionRepository,
                               BookRepository bookRepository,
                               ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public TransactionDTO createTransaction(TransactionDTO dto) {
        Transaction entity = TransactionMapper.toEntity(dto);
        entity.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(BookNotFoundException::new));
        entity.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(ClientNotFoundException::new));
        transactionRepository.save(entity);
        return dto;
    }

    public TransactionDTO readTransaction(long id) {
        Transaction entity = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        return TransactionMapper.toDTO(entity);
    }

    public TransactionsDTO readTransactionsByClientId(long id) {
        List<Transaction> list = transactionRepository.findTransactionsByClient_Id(id);
        return TransactionsMapper.toDTO(list);
    }

    public TransactionsDTO readTransactionsByBookId(long id) {
        List<Transaction> list = transactionRepository.findTransactionsByBook_Id(id);
        return TransactionsMapper.toDTO(list);
    }

    public TransactionDTO updateTransaction(TransactionDTO dto, long id) {
        Transaction entity = TransactionMapper.toEntity(dto);
        entity.setId(id);
        entity.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(BookNotFoundException::new));
        entity.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(ClientNotFoundException::new));
        transactionRepository.save(entity);
        return dto;
    }

    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }


}

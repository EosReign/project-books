package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.entity.Transaction;
import com.eosreign.projectbooks.repository.BookRepository;
import com.eosreign.projectbooks.repository.ClientRepository;
import com.eosreign.projectbooks.repository.TransactionRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {
    @Mock
    private TransactionRepository transactionRepository;
    @Mock
    private BookRepository bookRepository;
    @Mock
    private ClientRepository clientRepository;

    @InjectMocks
    private TransactionService transactionService;

    private TransactionDTO rightTransaction;
    private TransactionDTO wrongDateTake;
    private TransactionDTO emptyClientField;
    private TransactionDTO emptyBookField;
    private Transaction transactionEntity;
    private Book book;
    private Client client;

    private
    @BeforeEach
    void prepareData() {
        rightTransaction = new TransactionDTO(LocalDate.of(2023, 9, 27),
                                                        LocalDate.of(2023, 11, 27),
                                                        null, 1234l, null, 2341l, false);
        wrongDateTake = new TransactionDTO(LocalDate.of(1970, 9, 27),
                                                        LocalDate.of(2023, 11, 27),
                                                        null, 1234l, null, 2341l, false);
        emptyClientField = new TransactionDTO(LocalDate.of(2023, 9, 27),
                                                        LocalDate.of(2023, 11, 27),
                                                        null, null, null, 2341l, false);
        emptyBookField = new TransactionDTO(LocalDate.of(2023, 9, 27),
                                                        LocalDate.of(2023, 11, 27),
                                                        null, 24142l, null, null, false);
        transactionEntity = new Transaction(1l, LocalDate.of(2023, 9, 27),
                                                        LocalDate.of(2023, 11, 27),
                                            client, book, false);
        book = new Book(1l, "Мечтают ли андроиды об электроовцах?",
                "randomText", "Philip Kindred Dick", "Sueta", null);
        client = new Client();
    }
    @AfterAll
    void endData() {
        rightTransaction = null;
        wrongDateTake = null;
        emptyClientField = null;
        emptyBookField = null;
        book = null;
        client = null;
    }


    @Test
    public void createTransactionTest_Positive() {
        Mockito.when(transactionRepository.save(transactionEntity)).thenReturn(transactionEntity);
        assertThat(transactionEntity).isEqualTo(transactionService.createTransaction(rightTransaction));
        assertThat(transactionService.createTransaction(rightTransaction)).isNotNull();
    }
    @Test
    public void readTransactionTest() {

    }
    @Test
    public void readTransactionsByClientIdTest() {

    }
    @Test
    public void readTransactionsByBookIdTest() {

    }
    @Test
    public void updateTransactionTest() {

    }
    @Test
    public void deleteTransactionTest() {

    }
}

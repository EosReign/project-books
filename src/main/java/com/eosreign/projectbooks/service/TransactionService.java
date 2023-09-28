package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.dto.TransactionsDTO;
import com.eosreign.projectbooks.entity.Transaction;
import com.eosreign.projectbooks.exception.*;
import com.eosreign.projectbooks.mapper.TransactionMapper;
import com.eosreign.projectbooks.mapper.TransactionsMapper;
import com.eosreign.projectbooks.repository.BookRepository;
import com.eosreign.projectbooks.repository.ClientRepository;
import com.eosreign.projectbooks.repository.TransactionRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public TransactionDTO createTransaction(TransactionDTO dto) throws RuntimeException {
        checkData(dto);
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
        List<Transaction> list = transactionRepository.findTransactionsByClient_Id(id).orElseThrow(TransactionNotFoundException::new);
        return TransactionsMapper.toDTO(list);
    }

    public TransactionsDTO readTransactionsByBookId(long id) {
        List<Transaction> list = transactionRepository.findTransactionsByBook_Id(id).orElseThrow(TransactionNotFoundException::new);
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

    public TransactionDTO closeTransaction(long id) {
        Transaction entity = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        entity.setClosed(true);
        transactionRepository.save(entity);
        return TransactionMapper.toDTO(entity);
    }


    public void deleteTransaction(long id) {
        transactionRepository.deleteById(id);
    }

    private void checkData(TransactionDTO dto) {
        try {
            if (LocalDate.now().toEpochDay() > dto.getDateTake().toEpochDay()) throw new TransactionWrongDateTakeException();
            if (dto.isClosed()) throw new TransactionCreateMightBeUnclosedException();
            if (dto.getBookId() < 0 || dto.getBookId() != null) throw new TransactionInvalidBookId();
            if (dto.getClientId() < 0 || dto.getClientId() != null) throw new TransactionInvalidClientId();
            List<Transaction> checkList = transactionRepository.findTransactionsByBook_Id(dto.getBookId()).get();
            for (Transaction t: checkList) {
                if (!t.isClosed()) throw new BookIsBusyException();
            }
        } catch (TransactionWrongDateTakeException e) {
            System.out.println("Дата выдачи не может быть назначена раньше, чем нынешний день. ");
        } catch (TransactionCreateMightBeUnclosedException e) {
            System.out.println("Транзакция не может создаваться уже закрытой. ");
        } catch (TransactionInvalidBookId e) {
            System.out.println("Неправильно введен ID книги. ");
        } catch (TransactionInvalidClientId e) {
            System.out.println("Неправильно введен ID клиента. ");
        } catch (BookIsBusyException e) {
            System.out.println("Книга участвующая в транзакции еще не закрыта в другой. ");
        }
    }



}

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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class TransactionService implements TransactionServiceImpl {
    private final TransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    TransactionService(TransactionRepository transactionRepository,
                               BookRepository bookRepository,
                               ClientRepository clientRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TransactionDTO createTransaction(TransactionDTO dto) throws RuntimeException {
        checkData(dto);
        Transaction entity = TransactionMapper.toEntity(dto);
        entity.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(BookNotFoundException::new));
        entity.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(ClientNotFoundException::new));
        transactionRepository.save(entity);
        return dto;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TransactionDTO readTransaction(long id) {
        Transaction entity = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        return TransactionMapper.toDTO(entity);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TransactionsDTO readTransactionsByClientId(long id) {
        List<Transaction> list = transactionRepository.findTransactionsByClient_Id(id).orElseThrow(TransactionNotFoundException::new);
        return TransactionsMapper.toDTO(list);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TransactionsDTO readTransactionsByBookId(long id) {
        List<Transaction> list = transactionRepository.findTransactionsByBook_Id(id).orElseThrow(TransactionNotFoundException::new);
        return TransactionsMapper.toDTO(list);
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TransactionDTO updateTransaction(TransactionDTO dto, long id) {
        Transaction entity = TransactionMapper.toEntity(dto);
        entity.setId(id);
        entity.setBook(bookRepository.findById(dto.getBookId()).orElseThrow(BookNotFoundException::new));
        entity.setClient(clientRepository.findById(dto.getClientId()).orElseThrow(ClientNotFoundException::new));
        transactionRepository.save(entity);
        return dto;
    }
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public TransactionDTO closeTransaction(long id) {
        Transaction entity = transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
        entity.setClosed(true);
        transactionRepository.save(entity);
        return TransactionMapper.toDTO(entity);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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
            log.warn("Дата выдачи не может быть назначена раньше, чем нынешний день. ");
            throw new TransactionWrongDateTakeException();
        } catch (TransactionCreateMightBeUnclosedException e) {
            log.warn("Транзакция не может создаваться уже закрытой. ");
            throw new TransactionCreateMightBeUnclosedException();
        } catch (TransactionInvalidBookId e) {
            log.warn("Неправильно введен ID книги. ");
            throw new TransactionInvalidBookId();
        } catch (TransactionInvalidClientId e) {
            log.warn("Неправильно введен ID клиента. ");
            throw new TransactionInvalidClientId();
        } catch (BookIsBusyException e) {
            log.warn("Книга участвующая в транзакции еще не закрыта в другой. ");
            throw new BookIsBusyException();
        }
    }



}

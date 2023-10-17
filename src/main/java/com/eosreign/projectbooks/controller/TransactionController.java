package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.dto.TransactionsDTO;
import com.eosreign.projectbooks.service.TransactionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping()
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO dto) {
        log.info("Задействован метод addTransaction");
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping()
    public ResponseEntity<TransactionDTO> getTransaction(@RequestParam(name= "id") long id) {
        log.info("Задействован метод getTransaction");
        return ResponseEntity.ok(transactionService.readTransaction(id));
    }

    @GetMapping()
    public ResponseEntity<TransactionsDTO> getTransactionsByClientId(@RequestParam(name= "client_id") long id) {
        log.info("Задействован метод getTransactionsByClientId");
        return ResponseEntity.ok(transactionService.readTransactionsByClientId(id));
    }

    @GetMapping()
    public ResponseEntity<TransactionsDTO> getTransactionsByBookId(@RequestParam(name= "book_id") long id) {
        log.info("Задействован метод getTransactionsByBookId");
        return ResponseEntity.ok(transactionService.readTransactionsByBookId(id));
    }

    @PutMapping()
    public ResponseEntity<TransactionDTO> updateTransaction(@RequestBody TransactionDTO dto, @RequestParam(name="id") long id) {
        log.info("Задействован метод updateTransaction");
        return ResponseEntity.ok(transactionService.updateTransaction(dto, id));
    }

    @PutMapping("/close")
    public ResponseEntity<TransactionDTO> closeTransaction(@RequestParam(name="id") long id) {
        log.info("Задействован метод closeTransaction");
        return ResponseEntity.ok(transactionService.closeTransaction(id));
    }

    @DeleteMapping()
    public ResponseEntity<Void> deleteTransaction(@RequestParam(name="id") long id) {
        log.info("Задействован метод deleteTransaction");
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

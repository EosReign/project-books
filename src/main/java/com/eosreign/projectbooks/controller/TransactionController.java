package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.dto.TransactionsDTO;
import com.eosreign.projectbooks.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private final TransactionService transactionService;
    private TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/add")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody TransactionDTO dto) {
        return ResponseEntity.ok(transactionService.createTransaction(dto));
    }

    @GetMapping("/get")
    public ResponseEntity<TransactionDTO> getTransaction(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(transactionService.readTransaction(id));
    }

    @GetMapping("/get_transactions_by_client_id")
    public ResponseEntity<TransactionsDTO> getTransactionsByClientId(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(transactionService.readTransactionsByClientId(id));
    }

    @GetMapping("/get_transactions_by_book_id")
    public ResponseEntity<TransactionsDTO> getTransactionsByBookId(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(transactionService.readTransactionsByBookId(id));
    }

    @PutMapping("/update")
    public ResponseEntity<TransactionDTO> updateBook(@RequestBody TransactionDTO dto, @RequestParam(name="id") long id) {
        return ResponseEntity.ok(transactionService.updateTransaction(dto, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestParam(name="id") long id) {
        transactionService.deleteTransaction(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

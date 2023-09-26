package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.TransactionDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.entity.Transaction;
import com.eosreign.projectbooks.service.BooksTurnoverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books_turnover")
public class BooksTurnoverController {

    private final BooksTurnoverService booksTurnoverService;
    private BooksTurnoverController(BooksTurnoverService booksTurnoverService) {
        this.booksTurnoverService = booksTurnoverService;
    }

    @PostMapping("/add_transaction")
    public ResponseEntity<TransactionDTO> addTransaction(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(booksTurnoverService.createTransaction(transaction));
    }

    @GetMapping("/get")
    public ResponseEntity<BookDTO> getBook(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(booksTurnoverService.readBook(id));
    }

    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody Book book, @RequestParam(name="id") long id) {
        return ResponseEntity.ok(booksTurnoverService.updateBook(book, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestParam(name="id") long id) {
        booksTurnoverService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

     */
}

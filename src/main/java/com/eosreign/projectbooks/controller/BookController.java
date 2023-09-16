package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books/")
public class BookController {
    private BookService bookService;
    private BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("add")
    public ResponseEntity<BookDTO> addBook(@RequestBody Book book) {
        return ResponseEntity.ok(bookService.createBook(book));
    }

    @GetMapping("get")
    public ResponseEntity<BookDTO> getBook(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(bookService.readBook(id));
    }


    @PutMapping("update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody Book book, @RequestParam(name="id") long id) {
        return ResponseEntity.ok(bookService.updateBook(book, id));
    }

    @DeleteMapping("delete")
    public ResponseEntity<Void> updateBook(@RequestParam(name="id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

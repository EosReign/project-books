package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.BooksDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping()
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO dto) {
        log.info("Задействован метод addBook");
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBook(@PathVariable(name= "id") long id) {
        log.info("Задействован метод getBook");
        return ResponseEntity.ok(bookService.readBook(id));
    }

    @GetMapping()
    public ResponseEntity<BooksDTO> getBooks() {
        log.info("Задействован метод getBooks");
        return ResponseEntity.ok(bookService.readBooks());
    }

    @GetMapping("/{fulltext}")
    public ResponseEntity<BookDTO> getBookByFulltext(@PathVariable(name= "fulltext") String text) {
        log.info("Задействован метод getBookByFulltext");
        return ResponseEntity.ok(bookService.readBookByFulltext(text));
    }

    @GetMapping("/{author}")
    public ResponseEntity<BooksDTO> getBookByAuthor(@PathVariable(name= "author") String name) {
        log.info("Задействован метод getBook");
        return ResponseEntity.ok(bookService.readBooksByAuthor(name));
    }

    @GetMapping("/{publisher}")
    public ResponseEntity<BooksDTO> getBooksByPublisher(@PathVariable(name= "publisher") String name) {
        log.info("Задействован метод getBooksByPublisher");
        return ResponseEntity.ok(bookService.readBooksByPublisher(name));
    }


    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@RequestBody Book book, @PathVariable(name="id") long id) {
        log.info("Задействован метод updateBook");
        return ResponseEntity.ok(bookService.updateBook(book, id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable(name="id") long id) {
        log.info("Задействован метод deleteBook");
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.BooksDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;
    private BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping()
    public ResponseEntity<String> getPicture() {
        return ResponseEntity.ok("""
                .
                <br>══════════▄█▀▀▀▀▀▀█▄══▄▀▀▀▀▀▀▄
                <br>════════▄▀░░░░░░▄▄▄▄▀█░░░░░░░░▀▄
                <br>═══════█░░░░░░▀▀░░░░▀▀█▄▀▀▀▀▀▀▀█▄
                <br>══════█░░░░░░░░▄▄████████▄░▄███████▄
                <br>═════▄▀░░░░░░░▀███████████▄██████████▄
                <br>════█▀░░░░░▄▀▀█▀░▄█▄███▄░▀█░▄█▄███░░░█
                <br>═══█░░░░░░░▀▀█▀▀▄▄█████▄▄▀▀▄▄█████▀▀▀█
                <br>══█▀░░░░░░░░░░▀▄▄▄▄▄▄▄▄▄▄▀░░░░░░░░▄█▀
                <br>══█░░░░░░░░░░░░░░░░░░▄▀░░░░░░▀█▀▀▀█▄
                <br>══█░░░░░░░░░░░▄▄▄▄░░░░░░░░░░░░░░░░░█
                <br>══█░░░░░░░░▄▀▀░▄▄░▀▀▀▀▀▄▄▄▄▄▄▄▀▀▀▀▀▀█
                <br>══▀█░░░░░█░▀▄▀▀░░▀▀▀▀▀▄▄▄▄▄▄▄▄▄▄▄▄▄█
                <br>══▄█▄▄░░░▀▄░░▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀▄▄▄▄▄▄▀
                <br>▄▀▀░▀██▄░░░▀▀░░░░░░░░░░░░░░▄▄▄▀▀
                <br>░░░░░░▀▀███▄▄▄▄▄▄▄▄▄▄▄▄▄████▄
                <br>░░░░░░░░░░▀▀▀▀▀▀▀▀▀▀▀▀▀▀▀░░░▀█▄
                <br>░░░░░░░░░░░░░░░░░░░░░░░░░░░░░░█""");
    }
    @PostMapping("/add")
    public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO dto) {
        return ResponseEntity.ok(bookService.createBook(dto));
    }

    @GetMapping("/get")
    public ResponseEntity<BookDTO> getBook(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(bookService.readBook(id));
    }

    @GetMapping("/get_books")
    public ResponseEntity<BooksDTO> getBooks() {
        return ResponseEntity.ok(bookService.readBooks());
    }

    @GetMapping("/get_by_fulltext")
    public ResponseEntity<BookDTO> getBookByFulltext(@RequestParam(name= "fulltext") String text) {
        return ResponseEntity.ok(bookService.readBookByFulltext(text));
    }

    @GetMapping("/get_books_by_author")
    public ResponseEntity<BooksDTO> getBook(@RequestParam(name= "author") String name) {
        return ResponseEntity.ok(bookService.readBooksByAuthor(name));
    }

    @GetMapping("/get_books_by_publisher")
    public ResponseEntity<BooksDTO> getBooksByPublisher(@RequestParam(name= "publisher") String name) {
        return ResponseEntity.ok(bookService.readBooksByPublisher(name));
    }


    @PutMapping("/update")
    public ResponseEntity<BookDTO> updateBook(@RequestBody Book book, @RequestParam(name="id") long id) {
        return ResponseEntity.ok(bookService.updateBook(book, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteBook(@RequestParam(name="id") long id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.BooksDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.exception.BookNotFoundException;
import com.eosreign.projectbooks.mapper.BookMapper;
import com.eosreign.projectbooks.mapper.BooksMapper;
import com.eosreign.projectbooks.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceImpl {
    private final BookRepository bookRepository;
    private BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO createBook(Book book) {
        bookRepository.save(book);
        return BookMapper.toDTO(book);
    }
    public BookDTO readBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(BookNotFoundException::new);
        return BookMapper.toDTO(book);
    }

    public BooksDTO readBooks() {
        List<Book> books = bookRepository.findAll();
        return BooksMapper.toDTO(books);
    }

    public BookDTO readBookByFulltext(String text) {
        Book book = bookRepository.findBookByText(text);
        return BookMapper.toDTO(book);
    }
    public BooksDTO readBooksByAuthor(String name) {
        List<Book> books = bookRepository.findBooksByAuthorName(name);
        return BooksMapper.toDTO(books);
    }
    public BooksDTO readBooksByPublisher(String name) {
        List<Book> books = bookRepository.findBooksByPublisher(name);
        return BooksMapper.toDTO(books);
    }

    public BookDTO updateBook(Book book, long id) {
        book.setId(id);
        bookRepository.save(book);
        return BookMapper.toDTO(book);
    }
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.mapper.BookMapper;
import com.eosreign.projectbooks.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService implements BookServiceImpl {
    private BookRepository bookRepository;
    private BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO createBook(Book book) {
        bookRepository.save(book);
        return BookMapper.INSTANCE.toDTO(book);
    }
    public BookDTO readBook(long id) {
        Book book = bookRepository.findById(id).get();
        return BookMapper.INSTANCE.toDTO(book);
    }
    public BookDTO updateBook(Book book, long id) {
        book.setId(id);
        bookRepository.save(book);
        return BookMapper.INSTANCE.toDTO(book);
    }
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }
}

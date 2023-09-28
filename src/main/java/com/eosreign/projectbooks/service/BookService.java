package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.BooksDTO;
import com.eosreign.projectbooks.entity.Book;
import com.eosreign.projectbooks.exception.*;
import com.eosreign.projectbooks.mapper.BookMapper;
import com.eosreign.projectbooks.mapper.BooksMapper;
import com.eosreign.projectbooks.repository.BookRepository;
import com.eosreign.projectbooks.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService implements BookServiceImpl {
    private final BookRepository bookRepository;
    private final ClientRepository clientRepository;
    private BookService(BookRepository bookRepository,
                        ClientRepository clientRepository) {
        this.bookRepository = bookRepository;
        this.clientRepository = clientRepository;
    }

    public BookDTO createBook(BookDTO dto) {
        checkData(dto);
        Book entity = BookMapper.toEntity(dto);
        entity.setClient(clientRepository.findById(dto.getLibrary()).orElseThrow(ClientNotFoundException::new));
        bookRepository.save(entity);
        return dto;
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
        Book book = bookRepository.findBookByText(text).orElseThrow(BookNotFoundException::new);
        return BookMapper.toDTO(book);
    }
    public BooksDTO readBooksByAuthor(String name) {
        List<Book> books = bookRepository.findBooksByAuthorName(name).orElseThrow(BookNotFoundException::new);
        return BooksMapper.toDTO(books);
    }
    public BooksDTO readBooksByPublisher(String name) {
        List<Book> books = bookRepository.findBooksByPublisher(name).orElseThrow(BookNotFoundException::new);
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

    private void checkData(BookDTO dto) {
        try {
            if (dto.getText().isEmpty()) throw new BookTextIsEmptyException();
            if (dto.getName().isBlank()) throw new BookNameIsEmptyException();
            if (dto.getAuthorName().isEmpty()) throw new BookAuthorNameIsEmptyException();
        } catch (BookTextIsEmptyException e) {
            System.out.println("Текст книги пустой. ");
        } catch (BookNameIsEmptyException e) {
            System.out.println("Название книги отсутствует. ");
        } catch (BookAuthorNameIsEmptyException e) {
            System.out.println("Псевдоним/Имя автора отсутствуют. ");
        }

    }

}

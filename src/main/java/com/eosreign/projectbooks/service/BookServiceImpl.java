package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.dto.BooksDTO;
import com.eosreign.projectbooks.entity.Book;

public interface BookServiceImpl {
    BookDTO createBook(BookDTO dto);
    BookDTO readBook(long id);
    BooksDTO readBooks();
    BookDTO readBookByFulltext(String text);
    BooksDTO readBooksByAuthor(String name);
    BooksDTO readBooksByPublisher(String name);
    BookDTO updateBook(Book book, long id);
    void deleteBook(long id);
}

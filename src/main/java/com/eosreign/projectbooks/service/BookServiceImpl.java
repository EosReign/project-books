package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.BookDTO;
import com.eosreign.projectbooks.entity.Book;

public interface BookServiceImpl {
    BookDTO createBook(Book book);
    BookDTO readBook(long id);
    BookDTO updateBook(Book book, long id);
    void deleteBook(long id);
}

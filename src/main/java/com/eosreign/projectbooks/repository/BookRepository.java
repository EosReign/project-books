package com.eosreign.projectbooks.repository;

import com.eosreign.projectbooks.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findBookByText(String text);
    Optional<List<Book>> findBooksByAuthorName(String name);
    Optional<List<Book>> findBooksByPublisher(String name);
}

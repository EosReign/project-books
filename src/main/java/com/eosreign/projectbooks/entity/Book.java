package com.eosreign.projectbooks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "book_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name= "book_name")
    private String name;
    @Column(name= "book_text")
    private String text;
    @Column(name= "author_name")
    private String authorName;
    @Column(name = "publisher")
    private String publisher;
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;



}

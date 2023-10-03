package com.eosreign.projectbooks.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;

@Entity
@Table(name = "client_table")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name= "client_name")
    private String name;
    @Column(name= "client_address")
    private String address;
    @Column(name= "client_phone")
    private long phone;
    @Column(name= "password")
    private String password;
}

package com.eosreign.projectbooks.repository;
import com.eosreign.projectbooks.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    boolean existsClientByPhone(Long phone);
    Optional<Client> findClientByPhone(Long phone);
}

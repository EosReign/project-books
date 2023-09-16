package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.entity.Client;

public interface ClientServiceImpl {
    ClientDTO createClient(Client client);
    ClientDTO readClient(long id);
    ClientDTO updateClient(Client client, long id);
    void deleteClient(long id);
}

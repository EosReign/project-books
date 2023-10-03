package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.dto.RegisterReq;
import com.eosreign.projectbooks.entity.Client;

import java.util.List;

public interface ClientServiceImpl {
    void createClient(RegisterReq req);
    ClientDTO readClient(long id);
    ClientDTO updateClient(ClientDTO dto, long id);
    void deleteClient(long id);
    ClientsDTO readClients();
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.mapper.ClientMapper;
import com.eosreign.projectbooks.repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService implements ClientServiceImpl {
    private ClientRepository clientRepository;
    private ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO createClient(Client client) {
        clientRepository.save(client);
        return ClientMapper.INSTANCE.toDTO(client);
    }
    public ClientDTO readClient(long id) {
        Client client = clientRepository.findById(id).get();
        return ClientMapper.INSTANCE.toDTO(client);
    }
    public ClientDTO updateClient(Client client, long id) {
        client.setId(id);
        clientRepository.save(client);
        return ClientMapper.INSTANCE.toDTO(client);
    }
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.exception.ClientNotFoundException;
import com.eosreign.projectbooks.mapper.ClientMapper;
import com.eosreign.projectbooks.mapper.ClientsMapper;
import com.eosreign.projectbooks.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService implements ClientServiceImpl {
    private final ClientRepository clientRepository;
    private ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO createClient(Client client) {
        clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }
    public ClientDTO readClient(long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return ClientMapper.toDTO(client);
    }

    public ClientsDTO readClients() {
        List<Client> arr = clientRepository.findAll();
        return ClientsMapper.toDTO(arr);
    }

    public ClientDTO updateClient(Client client, long id) {
        client.setId(id);
        clientRepository.save(client);
        return ClientMapper.toDTO(client);
    }
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.exception.ClientAddressIsEmptyException;
import com.eosreign.projectbooks.exception.ClientNameIsEmptyException;
import com.eosreign.projectbooks.exception.ClientNotFoundException;
import com.eosreign.projectbooks.exception.ClientPhoneIsEmptyException;
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

    public ClientDTO createClient(ClientDTO dto) {
        checkData(dto);
        Client entity = ClientMapper.toEntity(dto);
        clientRepository.save(entity);
        return dto;
    }
    public ClientDTO readClient(long id) {
        Client client = clientRepository.findById(id).orElseThrow(ClientNotFoundException::new);
        return ClientMapper.toDTO(client);
    }

    public ClientsDTO readClients() {
        List<Client> arr = clientRepository.findAll();
        return ClientsMapper.toDTO(arr);
    }

    public ClientDTO updateClient(ClientDTO dto, long id) {
        Client entity = ClientMapper.toEntity(dto);
        entity.setId(id);
        clientRepository.save(entity);
        return dto;
    }
    public void deleteClient(long id) {
        clientRepository.deleteById(id);
    }

    private void checkData(ClientDTO dto) {
        try {
            if (dto.getName().isBlank()) throw new ClientNameIsEmptyException();
            if (dto.getPhone().toString().isBlank()) throw new ClientPhoneIsEmptyException();
            if (dto.getAddress().isBlank()) throw new ClientAddressIsEmptyException();
        } catch (ClientNameIsEmptyException e) {
            System.out.println("Введеное имя клиента пустое. ");
        } catch (ClientPhoneIsEmptyException e) {
            System.out.println("Введеный номер телефона пустой. ");
        } catch (ClientAddressIsEmptyException e) {
            System.out.println("Введеный адрес пустой. ");
        }
    }
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.dto.NewPassword;
import com.eosreign.projectbooks.dto.RegisterReq;
import com.eosreign.projectbooks.entity.Authority;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.exception.ClientAddressIsEmptyException;
import com.eosreign.projectbooks.exception.ClientNameIsEmptyException;
import com.eosreign.projectbooks.exception.ClientNotFoundException;
import com.eosreign.projectbooks.exception.ClientPhoneIsEmptyException;
import com.eosreign.projectbooks.mapper.ClientMapper;
import com.eosreign.projectbooks.mapper.ClientsMapper;
import com.eosreign.projectbooks.mapper.RegisterReqMapper;
import com.eosreign.projectbooks.repository.AuthorityRepository;
import com.eosreign.projectbooks.repository.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ClientService implements ClientServiceImpl {
    private final ClientRepository clientRepository;
    private final AuthorityRepository authorityRepository;
    private ClientService(ClientRepository clientRepository, AuthorityRepository authorityRepository) {
        this.clientRepository = clientRepository;
        this.authorityRepository = authorityRepository;
    }

    public void createClient(RegisterReq req) {
        checkData(req);
        Client entity = RegisterReqMapper.toEntity(req);
        clientRepository.save(entity);
        authorityRepository.save(RegisterReqMapper.toAuthority(req));
    }

    //TODO прикрутить authentication
    public NewPassword setPassword(NewPassword pass) {
        return pass;
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

    private void checkData(RegisterReq req) {
        try {
            if (req.getName().isBlank()) throw new ClientNameIsEmptyException();
            if (req.getPhone().toString().isBlank()) throw new ClientPhoneIsEmptyException();
            if (req.getAddress().isBlank()) throw new ClientAddressIsEmptyException();
        } catch (ClientNameIsEmptyException e) {
            log.warn("Введеное имя клиента пустое. ");
            throw new ClientNameIsEmptyException();
        } catch (ClientPhoneIsEmptyException e) {
            log.warn("Введеный номер телефона пустой. ");
            throw new ClientPhoneIsEmptyException();
        } catch (ClientAddressIsEmptyException e) {
            log.warn("Введеный адрес пустой. ");
            throw new ClientAddressIsEmptyException();
        }
    }
}

package com.eosreign.projectbooks.service;

import com.eosreign.projectbooks.dto.LoginReq;
import com.eosreign.projectbooks.dto.RegisterReq;
import com.eosreign.projectbooks.entity.Authority;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.exception.ClientAlreadyExistException;
import com.eosreign.projectbooks.exception.ClientNotFoundException;
import com.eosreign.projectbooks.exception.ClientWrongInputPassword;
import com.eosreign.projectbooks.repository.AuthorityRepository;
import com.eosreign.projectbooks.repository.ClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    private static final Logger log = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final PasswordEncoder encoder;
    private final ClientService clientService;
    private final ClientRepository clientRepository;

    public AuthServiceImpl(ClientService clientService, PasswordEncoder encoder,
                           ClientRepository clientRepository) {
        this.encoder = encoder;
        this.clientService = clientService;
        this.clientRepository = clientRepository;
    }

    @Override
    public LoginReq login(LoginReq req) {
        try {
            Client client = clientRepository.findClientByPhone(req.getPhone()).orElseThrow(ClientNotFoundException::new);
            String encryptedPassword = client.getPassword();
            if (!encoder.matches(req.getPassword(), encryptedPassword)) throw new ClientWrongInputPassword();
            req.setPassword(encryptedPassword);

        } catch (ClientNotFoundException e) {
            log.warn("Error: client with phone: " + req.getPhone() + " doesn't exist.");
            throw new ClientNotFoundException();
        } catch (ClientWrongInputPassword e) {
            log.warn("Error: Entered wrong password.");
            throw new ClientWrongInputPassword();
        }

        return req;
    }

    @Override
    public RegisterReq register(RegisterReq registerReq) {
        try {
            if (clientRepository.existsClientByPhone(registerReq.getPhone())) throw new ClientAlreadyExistException();
            registerReq.setPassword(encoder.encode(registerReq.getPassword()));
            clientService.createClient(registerReq);

        } catch (ClientAlreadyExistException e) {
            log.warn("Error: " + registerReq.getName() + " - is already exist.");
            throw new ClientAlreadyExistException();
        }

        return registerReq;
    }
}

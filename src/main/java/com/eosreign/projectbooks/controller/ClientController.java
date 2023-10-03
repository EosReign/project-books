package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.dto.NewPassword;
import com.eosreign.projectbooks.service.ClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword pass) {
        log.info("Задействован метод setPassword");
        return ResponseEntity.ok(clientService.setPassword(pass));
    }

    @GetMapping("/get")
    public ResponseEntity<ClientDTO> getClient(@RequestParam(name= "id") long id) {
        log.info("Задействован метод getClient");
        return ResponseEntity.ok(clientService.readClient(id));
    }

    @GetMapping("/get_clients")
    public ResponseEntity<ClientsDTO> getClients() {
        log.info("Задействован метод getClients");
        return ResponseEntity.ok(clientService.readClients());
    }


    @PutMapping("/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody ClientDTO dto, @RequestParam(name="id") long id) {
        log.info("Задействован метод updateClient");
        return ResponseEntity.ok(clientService.updateClient(dto, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteClient(@RequestParam(name="id") long id) {
        log.info("Задействован метод deleteClient");
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

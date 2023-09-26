package com.eosreign.projectbooks.controller;

import com.eosreign.projectbooks.dto.ClientDTO;
import com.eosreign.projectbooks.dto.ClientsDTO;
import com.eosreign.projectbooks.entity.Client;
import com.eosreign.projectbooks.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDTO> addClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @GetMapping("/get")
    public ResponseEntity<ClientDTO> getClient(@RequestParam(name= "id") long id) {
        return ResponseEntity.ok(clientService.readClient(id));
    }

    @GetMapping("/get_clients")
    public ResponseEntity<ClientsDTO> getClients() {
        return ResponseEntity.ok(clientService.readClients());
    }


    @PutMapping("/update")
    public ResponseEntity<ClientDTO> updateClient(@RequestBody Client client, @RequestParam(name="id") long id) {
        return ResponseEntity.ok(clientService.updateClient(client, id));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteClient(@RequestParam(name="id") long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

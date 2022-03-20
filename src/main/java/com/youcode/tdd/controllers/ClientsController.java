package com.youcode.tdd.controllers;

import com.youcode.tdd.entities.Client;
import com.youcode.tdd.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/clients")
public class ClientsController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public List<Client> getClients(){

        return clientService.getClients();
    }

    @PostMapping("/save")
    public void addClient(@RequestBody Client client){

        clientService.newClient(client);
    }

    @GetMapping("{clientId:^\\d+$}")
    public Optional<Client> getClientById(@PathVariable("clientId") Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("{email:.+@.+\\..+}")
    public Optional<Client> getClientByEmail(@PathVariable("email") String email) {
        return clientService.getClientByEmail(email);
    }

    public void updateClient(@PathVariable Long id,@RequestParam String name,String email){
        clientService.updateClient(id, name, email);

    }
    @DeleteMapping(path = "{id}")
    public void deleteClient(@PathVariable("id") Long id){
        clientService.deleteClient(id);
    }

}

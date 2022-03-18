package com.youcode.tdd.controllers;

import com.youcode.tdd.entities.Client;
import com.youcode.tdd.services.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public void addClient(@RequestBody Client client){

        clientService.newClient(client);
    }

    public void updateClient(@PathVariable Long id,@RequestParam String name,String email){
        clientService.updateClient(id, name, email);

    }
    @DeleteMapping(path = "{id}")
    public void deleteClient(@PathVariable("id") Long id){
        clientService.deleteClient(id);
    }

}

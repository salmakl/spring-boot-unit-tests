package com.youcode.tdd.services;

import com.youcode.tdd.entities.Client;
import com.youcode.tdd.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ClientService {

    @Autowired
    ClientRepository clientRepository;


    public List<Client> getClients(){
        return clientRepository.findAll();
    }

    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    public List<Client> getClientsByGender(String sex) {
        return clientRepository.findAllByGender(sex);
    }

    public Client newClient(Client client){

        Optional<Client> email =clientRepository.findByEmail(client.getEmail());
        if (email.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return clientRepository.save(client);
    }


    public void updateClient(Long id, Client client) {

        Client updateClient = clientRepository.getById(id);

        updateClient.setFullName(client.getFullName());
        updateClient.setEmail(client.getEmail());
        updateClient.setGender(client.getGender());
        updateClient.setPhone(client.getPhone());
        updateClient.setActive(client.isActive());

        clientRepository.save(updateClient);
    }

    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found");
        }

        client.get().setActive(false);
    }



}

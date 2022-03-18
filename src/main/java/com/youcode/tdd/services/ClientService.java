package com.youcode.tdd.services;

import com.youcode.tdd.entities.Client;
import com.youcode.tdd.repositories.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Client getClientById(Long id){
        Optional<Client> client = clientRepository.findById(id);
        return client.get();
    }
    public Client getClientByEmail(String email){
        Optional<Client> client = clientRepository.findByEmail(email);
        return client.get();
    }



    public Client newClient(Client client){

        Optional<Client> email =clientRepository.findByEmail(client.getEmail());
        if (email.isPresent()){
            throw new IllegalStateException("email taken");
        }
        return clientRepository.save(client);
    }

    @Transactional
    public void updateClient(Long id, String name, String email) {
        Client client = clientRepository.findById(id).orElseThrow(()->
                new IllegalStateException("Client with id " + id + " does not exist"));

        if(name!=null && name.length() > 0 && !Objects.equals(name, client.getFullName())){
            client.setFullName(name);
        }
        if(email!=null && email.length() > 0 && !Objects.equals(email, client.getEmail())){
            Optional<Client> clientByEmail= clientRepository.findByEmail(client.getEmail());
            if(clientByEmail.isPresent()){
                throw new IllegalStateException("Student with email " + client.getEmail() + " already exists");
            }
            client.setEmail(email);
        }

    }

    public void deleteClient(Long id){

        boolean exists = clientRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Client not found");
        }

        clientRepository.deleteById(id);
    }
}

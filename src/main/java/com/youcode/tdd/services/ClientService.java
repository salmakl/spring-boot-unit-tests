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
  /*  @Transactional
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

    }*/

  /*  public void deleteClient(Long id){

        boolean exists = clientRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Client not found");
        }

        clientRepository.deleteById(id);
    }*/

    public void deleteClient(Long id) {
        Optional<Client> client = clientRepository.findById(id);

        if (client.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "client not found");
        }

        client.get().setActive(false);
    }


}

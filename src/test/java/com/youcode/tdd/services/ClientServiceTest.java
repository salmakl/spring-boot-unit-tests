package com.youcode.tdd.services;


import static org.mockito.Mockito.when;

import com.youcode.tdd.entities.Client;
import com.youcode.tdd.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @Test
    public void saveClientTest(){

        Client client=new Client (1L,"salma@gmail.com","11111111","Salma",23,"Female",true);

        when(clientRepository.save(client)).thenReturn(client);
        assertEquals(client, clientService.newClient(client));
    }

    @Test
    public void getClientsTest() {

        Client client=new Client (1L,"salma@gmail.com","11111111","Salma",23,"Female",true);
        Client client1=new Client (2L,"salma@gmail.com","11111111","Hamza",23,"Female",true);

        List<Client> list = new ArrayList<Client>();
        list.add(client);
        list.add(client1);


        when(clientRepository.findAll()).thenReturn(list);
        assertEquals(2, clientService.getClients().size());
    }

    @Test
    public void getClientByIdTest() {

        Optional<Client> client = Optional.of(new Client (1L,"salma@gmail.com","11111111","Salma",23,"Female",true));


        when(clientRepository.findById(1L)).thenReturn(client);
        assertEquals(client, clientService.getClientById(1L));

    }

    @Test
    public void getClientByEmailTest() {

        Optional<Client> client = Optional.of(new Client (1L,"salma@gmail.com","11111111","Salma",23,"Female",true));


        when(clientRepository.findByEmail("salma@gmail.com")).thenReturn(client);
        assertEquals(client, clientService.getClientByEmail("salma@gmail.com"));

    }






}
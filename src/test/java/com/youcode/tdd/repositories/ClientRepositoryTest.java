package com.youcode.tdd.repositories;

import com.youcode.tdd.entities.Client;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class ClientRepositoryTest {

    @Mock
    private ClientRepository clientRepository;

    @Test
    void getAllClients() {
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12, "Homme",true);
        Client client2 = new Client(2L, "test2@gmail.com","+212687345087","test2",13,"Homme",true);
        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        Mockito.when(clientRepository.findAll()).thenReturn(clients);
        assertThat(clientRepository.findAll()).isNotNull();
    }

    @Test
    void addClient() {
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,"Homme",true);
        Mockito.when(clientRepository.save(client1))
                .thenReturn(client1);
        assertThat(clientRepository.save(client1)).isEqualTo(client1);
    }

    @Test
    void deleteClient() {
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,"female",true);
        clientRepository.deleteById(client1.getId());
        assertThat(clientRepository.getById(client1.getId())).isNull();
    }

    @Test
    void findById(){
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,"female",true);
        Mockito.when(clientRepository.findById(client1.getId())).thenReturn(Optional.of(client1));
        assertThat(clientRepository.findById(client1.getId())).isNotNull();

    }


    @Test
    void findByEmailTest(){
        Client client1 = new Client(1L, "test1@gmail.com","+212659697087","test1",12,"female",true);
        Mockito.when(clientRepository.findByEmail("test1@gmail.com")).thenReturn(Optional.of(client1));
        assertThat(clientRepository.findByEmail("test1@gmail.com").get().getEmail()).isEqualTo("test1@gmail.com");
    }

}
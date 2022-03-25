package com.youcode.tdd.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.youcode.tdd.entities.Client;
import com.youcode.tdd.services.ClientService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@RunWith(MockitoJUnitRunner.Silent.class)
@WebMvcTest(ClientsController.class)
class ClientsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientService clientService;

    @Test
    void getClients() throws Exception {
        Client client = new Client(1L, "salma@gmail.com", "11111111", "Salma", 23, "Female", true);
        Client client1 = new Client(2L, "salma@gmail.com", "11111111", "salmaa", 23, "Female", true);

        List<Client> list = new ArrayList<>();

        list.add(client);
        list.add(client1);

        when(clientService.getClients()).thenReturn(list);
        mockMvc.perform(get("/api/v1/clients"))
                .andExpect(status().isOk());

    }

    @Test
    void addClient() throws Exception {
        Client client = new Client(1L, "test1@gmail.com", "+212659697087", "test1", 12, "female", true);
        when(clientService.newClient(client))
                .thenReturn(client);

        mockMvc.perform(post("/api/v1/clients/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(client)))
                .andExpect(status().isOk());
    }

    @Test
    void getClientById() throws Exception {
        Client client = new Client(1L, "test1@gmail.com", "+212659697087", "test1", 12, "female", true);
        when(clientService.getClientById(client.getId())).thenReturn(java.util.Optional.of(client));
        mockMvc.perform(get("/api/v1/clients/" + client.getId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getClientByEmail() throws Exception {
        Client client = new Client(1L, "test1@gmail.com","+212659697087","test1",12,"male",true);
        when(clientService.getClientByEmail(client.getEmail())).thenReturn(java.util.Optional.of(client));
        mockMvc.perform(get("/api/v1/clients/" + client.getEmail())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }


}
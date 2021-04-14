package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ClientDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class ClientsRepositoryImpl implements ClientsRepository{
    List<ClientDTO> clients = new ArrayList<>();

    public ClientsRepositoryImpl() throws IOException {
        clients = loadDataBase();
    }

    private List<ClientDTO> loadDataBase() throws IOException {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:dbClients.json");

        } catch (IOException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ClientDTO>> typeReference = new TypeReference<>(){};
        List<ClientDTO> productDTOS = null;

        try{
            productDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e){
            e.printStackTrace();
        }
        return productDTOS;
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
        clientDTO.setId();
        clients.add(clientDTO);
        return clientDTO;
    }

    @Override
    public ClientDTO getClientByEmail(String email) {
        for (ClientDTO client : clients) {
            if (client.getEmail().equals(email)) return client;
        }
        return null;
    }

    @Override
    public List<ClientDTO> getAllClients() {
        return clients;
    }

    @Override
    public List<ClientDTO> getClientByProvince(String province) {
        List<ClientDTO> clientsProvince = new ArrayList<>();
        for (ClientDTO client : clients) {
            if (client.getProvince().toLowerCase(Locale.ROOT).equals(province.toLowerCase(Locale.ROOT))) clientsProvince.add(client);
        }
        return clientsProvince;
    }
}

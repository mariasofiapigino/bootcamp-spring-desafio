package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ClientDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository
public class ClientsRepositoryImpl implements ClientsRepository{
    List<ClientDTO> clients = new ArrayList<>();

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
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

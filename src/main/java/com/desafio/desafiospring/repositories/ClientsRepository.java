package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ClientDTO;

import java.util.List;

public interface ClientsRepository {
    ClientDTO addClient(ClientDTO clientDTO);

    ClientDTO getClientByEmail(String email);

    List<ClientDTO> getAllClients();

    List<ClientDTO> getClientByProvince(String province);
}

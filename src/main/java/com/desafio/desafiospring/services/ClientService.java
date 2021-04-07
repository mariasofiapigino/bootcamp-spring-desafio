package com.desafio.desafiospring.services;

import com.desafio.desafiospring.dtos.ClientDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidClient;

import java.util.List;

public interface ClientService {
    ClientDTO addClient(ClientDTO clientDTO) throws InvalidClient;

    List<ClientDTO> getAllClients() throws DataNotFound;

    List<ClientDTO> getClientByProvince(String province) throws DataNotFound;
}

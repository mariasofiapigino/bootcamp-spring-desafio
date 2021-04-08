package com.desafio.desafiospring.services;

import com.desafio.desafiospring.dtos.ClientDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidClient;
import com.desafio.desafiospring.repositories.ClientsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService{
    @Autowired
    private ClientsRepository clientsRepository;

    @Override
    // Primero se controla que no falten datos y que el cliente no este registrado
    // Luego se lo crea
    public ClientDTO addClient(ClientDTO clientDTO) throws InvalidClient {
        if (missingData(clientDTO)) throw new InvalidClient("Faltan datos del cliente");
        if (existingClient(clientDTO)) throw new InvalidClient("Cliente ya existente");
        return clientsRepository.addClient(clientDTO);
    }

    @Override
    // Obtiene el listado de clientes completo
    public List<ClientDTO> getAllClients() throws DataNotFound {
        if (clientsRepository.getAllClients().isEmpty()) throw new DataNotFound("No hay clientes registrados");
        return clientsRepository.getAllClients();
    }

    @Override
    // Obtiene el listado de clientes de determinada provincia
    public List<ClientDTO> getClientByProvince(String province) throws DataNotFound {
        if (clientsRepository.getClientByProvince(province).isEmpty()) throw new DataNotFound("No se encontraron clientes de la provincia de " + province);
        return clientsRepository.getClientByProvince(province);
    }

    // Todos los campos son obligatorios
    private boolean missingData(ClientDTO clientDTO) {
        return (clientDTO.getName() == null ||
                clientDTO.getLastName() == null ||
                clientDTO.getProvince() == null ||
                clientDTO.getEmail() == null);
    }

    // Considero que un cliente ya existe cuando su email ya está registrado
    private boolean existingClient(ClientDTO clientDTO) {
        ClientDTO clientEmail = clientsRepository.getClientByEmail(clientDTO.getEmail());
        return clientEmail != null;
    }
}

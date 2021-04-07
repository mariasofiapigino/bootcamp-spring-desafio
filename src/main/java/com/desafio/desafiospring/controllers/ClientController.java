package com.desafio.desafiospring.controllers;

import com.desafio.desafiospring.dtos.ClientDTO;
import com.desafio.desafiospring.dtos.StatusCodeDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidClient;
import com.desafio.desafiospring.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v1/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @PostMapping("/new")
    public ResponseEntity addClient(@RequestBody ClientDTO clientDTO) throws InvalidClient {
        return ResponseEntity.ok(clientService.addClient(clientDTO));
    }

    @GetMapping("/all")
    public ResponseEntity getAllClients() throws DataNotFound {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/province")
    public ResponseEntity getClientsProvince(@RequestParam String province) throws DataNotFound {
        return ResponseEntity.ok(clientService.getClientByProvince(province));
    }

    @ExceptionHandler(InvalidClient.class)
    public ResponseEntity<StatusCodeDTO> handleException(InvalidClient e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(400, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<StatusCodeDTO> handleException(DataNotFound e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

}

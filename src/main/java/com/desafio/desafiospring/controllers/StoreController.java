package com.desafio.desafiospring.controllers;

import com.desafio.desafiospring.dtos.ArticlesDTO;
import com.desafio.desafiospring.dtos.StatusCodeDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.exceptionsHandler.InvalidProduct;
import com.desafio.desafiospring.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/articles")
    public ResponseEntity getProducts(@RequestParam(required = false) Map<String, String> params) throws Exception {
        return ResponseEntity.ok(storeService.getProducts(params));
    }

    @PostMapping("/purchase-request")
    public ResponseEntity purchase(@RequestBody ArticlesDTO articles) throws IOException, InvalidProduct {
        return ResponseEntity.ok(storeService.getTicket(articles));
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity<StatusCodeDTO> handleException(IOException e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(DataNotFound.class)
    public ResponseEntity<StatusCodeDTO> handleException(DataNotFound e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(InvalidProduct.class)
    public ResponseEntity<StatusCodeDTO> handleException(InvalidProduct e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);
    }

    @ExceptionHandler(InvalidFilter.class)
    public ResponseEntity<StatusCodeDTO> handleException(InvalidFilter e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(400, e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }
}

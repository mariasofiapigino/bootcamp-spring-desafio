package com.desafio.desafiospring.controllers;

import com.desafio.desafiospring.dtos.ErrorDTO;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class StoreController {
    @Autowired
    private StoreService storeService;

    @GetMapping("/articles")
    public ResponseEntity getProducts(@RequestParam(required = false) Map<String, String> params) throws Exception {
        return ResponseEntity.ok(storeService.getProductsOrdered(params));
    }

    @ExceptionHandler(InvalidFilter.class)
    public ResponseEntity<ErrorDTO> handleException(InvalidFilter e) {
        ErrorDTO errorDTO = new ErrorDTO("Filtro invalido", e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }
}

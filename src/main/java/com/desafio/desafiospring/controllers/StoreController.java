package com.desafio.desafiospring.controllers;

import com.desafio.desafiospring.dtos.ArticlesDTO;
import com.desafio.desafiospring.dtos.StatusCodeDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.exceptionsHandler.InvalidProduct;
import com.desafio.desafiospring.exceptionsHandler.ServerError;
import com.desafio.desafiospring.services.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

/**
 * Hay tanto comentarios como un README.md que facilitan su entendimiento y ejecución
 *
 * @author  Sofia Pigino
 * @version 1.0
 */

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
    public ResponseEntity purchase(@RequestBody ArticlesDTO articles) throws IOException, InvalidProduct, ServerError {
        return ResponseEntity.ok(storeService.getTicket(articles));
    }

    @GetMapping("/shopping-cart")
    public ResponseEntity getShoppingCart() throws DataNotFound {
        return ResponseEntity.ok(storeService.getShoppingCart());
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

    @ExceptionHandler(ServerError.class)
    public ResponseEntity<StatusCodeDTO> handleException(ServerError e) {
        StatusCodeDTO errorDTO = new StatusCodeDTO(400, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }
}

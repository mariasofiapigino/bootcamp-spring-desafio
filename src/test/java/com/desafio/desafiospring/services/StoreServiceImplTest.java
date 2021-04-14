package com.desafio.desafiospring.services;

import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.repositories.ProductsRepository;
import com.desafio.desafiospring.repositories.ProductsRepositoryImpl;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@EnableWebMvc
class StoreServiceImplTest {

    @InjectMocks
    StoreService service;

    @Mock
    ProductsRepository productsRepository;

    private static List<ProductDTO> productsList;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        productsList = objectMapper.readValue(new File("src/main/resources/productsTest.json"), new TypeReference<List<ProductDTO>>() {
        });
    }

    @Test
    public void shouldListProducts() throws IOException {
        when(productsRepository.getProducts()).thenReturn(productsList);
    }

}
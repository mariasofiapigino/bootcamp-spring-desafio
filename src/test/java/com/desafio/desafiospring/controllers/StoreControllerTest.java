package com.desafio.desafiospring.controllers;

import java.lang.reflect.Type;
import java.util.*;

import com.desafio.desafiospring.controllers.StoreController;
import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.services.StoreService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StoreController.class)
public class StoreControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StoreService service;

    private static List<ProductDTO> productsList;
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeAll
    static void setUp() throws IOException {
        productsList = objectMapper.readValue(new File("src/main/resources/productsTest.json"), new TypeReference<List<ProductDTO>>() {
        });
    }

    @Test
    public void shouldListProducts() throws Exception {
        //mock service
        when(service.getProducts(any())).thenReturn(productsList);

        //get /api/v1/articles
        //map into objects
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/articles"))
                .andExpect(status().isOk()).andReturn();

        List<ProductDTO> responseArticles =
                objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });
        //assert equals
        Assertions.assertEquals(responseArticles, productsList);
    }

    // No tiene mucho sentido
    @Test
    public void shouldListProductsFilter() throws Exception {
        List<ProductDTO> filterProducts = productsList.stream().filter(product -> product.getCategory().toLowerCase(Locale.ROOT).equals("herramientas")).collect(Collectors.toList());

        //mock service
        when(service.getProducts(any())).thenReturn(filterProducts);

        //get /api/v1/articles
        //map into objects
        MvcResult mvcResult = mockMvc.perform(get("/api/v1/articles?category=Herramientas"))
                .andExpect(status().isOk()).andReturn();

        List<ProductDTO> responseArticles =
                objectMapper.readValue(mvcResult.getResponse().getContentAsByteArray(), new TypeReference<>() {
                });

        //assert equals
        Assertions.assertEquals(responseArticles, filterProducts);
    }
}
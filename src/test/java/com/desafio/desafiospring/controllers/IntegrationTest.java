package com.desafio.desafiospring.controllers;

import com.desafio.desafiospring.repositories.ProductsRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class IntegrationTest {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ProductsRepository repository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    // Hace el test de integracion del metodo getProducts() sin ningun filtro
    // Tiene problemas cuando la venta se ejecuta antes porque reduce el stock
    public void testListAllArticles() throws Exception {
        Path path = Paths.get("src/test/resources/allProducts.json");

        String allArticlesMockResponse = Files.readString(path);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/articles"))
                .andExpect(status().isOk())
                .andExpect(content().json(allArticlesMockResponse));
    }

    @Test
    // Hace el test de integracion del metodo getProducts() con los
    // filtros Category = Herramientas y freeShipping = true
    public void testListArticlesWithfilters() throws Exception {
        Path path = Paths.get("src/test/resources/filteredByCategoryProducts.json");

        String allArticlesWithFiltersMockResponse = Files.readString(path);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/articles?category=Herramientas&freeShipping=true"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allArticlesWithFiltersMockResponse));
    }

    @Test
    // Test de cuando se hace una consulta y no hay datos
    public void testDataNotFound() throws Exception {
        Path path = Paths.get("src/test/resources/dataNotFound.json");

        String articleNotFound = Files.readString(path);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/articles?freeShipping=false&brand=Black %26 Decker"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(articleNotFound));
    }

    @Test
    // Test de cuando se hace una consulta con filtros erroneos
    public void testInvalidFilter() throws Exception {
        Path path = Paths.get("src/test/resources/invalidFilter.json");

        String articleNotFound = Files.readString(path);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/api/v1/articles?brandy=Black %26 Decker"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json(articleNotFound));
    }

    @Test
    // Test del POST si mandamos un producto invalido
    void testInavlidProduct() throws Exception {
        Path pathRequest = Paths.get("src/test/resources/invalidProduct.json");
        String purchaseRequest = Files.readString(pathRequest);

        Path pathResponse = Paths.get("src/test/resources/invalidProductResponse.json");
        String purchaseResponse = Files.readString(pathResponse);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseRequest))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().json(purchaseResponse));
    }

    @Test
    // Test de cuando creamos una solicitud de compra exitosamente
    void testCreatePurchaseRequest() throws Exception {
        Path pathRequest = Paths.get("src/test/resources/newPurchase.json");
        String purchaseRequest = Files.readString(pathRequest);

        Path pathResponse = Paths.get("src/test/resources/newPurchaseResponse.json");
        String purchaseResponse = Files.readString(pathResponse);

        this.mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/api/v1/purchase-request")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(purchaseRequest))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(purchaseResponse));
    }



}

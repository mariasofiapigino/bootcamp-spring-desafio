package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.exceptionsHandler.InvalidProduct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductsRepository {

    List getProducts() throws IOException;

    List<ProductDTO> getProductsByFilter(String param1, String value1, String param2, String value2) throws IOException, DataNotFound, InvalidFilter;

    ProductDTO getProductById(Integer productId) throws IOException, InvalidProduct;
}

package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ProductDTO;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface ProductsRepository {

    List getProducts() throws IOException;

    List<ProductDTO> getProductsByTwoFilter(String param1, String value1, String param2, String value2) throws IOException;

    List<ProductDTO> getProductsByOneFilter(String param1, String value1) throws IOException;
}

package com.desafio.desafiospring.services;

import com.desafio.desafiospring.dtos.ArticlesDTO;
import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.dtos.ProductResponseDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.exceptionsHandler.InvalidProduct;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface StoreService {
    Object getProducts(Map<String, String> params) throws IOException, InvalidFilter, DataNotFound;

    List<ProductDTO> getProductsOrdered(List<ProductDTO> products, String order) throws IOException, InvalidFilter, DataNotFound;

    Object getTotalPrice(List<ProductResponseDTO> articles) throws IOException, InvalidProduct;

    Object getTicket(ArticlesDTO articles) throws IOException, InvalidProduct;
}

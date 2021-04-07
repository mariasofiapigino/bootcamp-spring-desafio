package com.desafio.desafiospring.services;

import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;

import java.io.IOException;
import java.util.Map;

public interface StoreService {
    Object getProducts(Map<String, String> params) throws IOException, InvalidFilter;

    Object getProductsOrdered(Map<String, String> params) throws IOException, InvalidFilter, DataNotFound;
}

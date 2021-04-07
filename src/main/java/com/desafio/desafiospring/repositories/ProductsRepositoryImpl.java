package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.utils.Filter;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class ProductsRepositoryImpl implements ProductsRepository {
    private List<ProductDTO> repository = new ArrayList<>();

    private List<ProductDTO> loadDataBase() throws IOException {
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:dbProductos.json");

        } catch (IOException e){
            e.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<ProductDTO>> typeReference = new TypeReference<>(){};
        List<ProductDTO> productDTOS = null;

        try{
            productDTOS = objectMapper.readValue(file, typeReference);
        } catch (IOException e){
            e.printStackTrace();
        }
        return productDTOS;
    }

    @Override
    public List<ProductDTO> getProducts() throws IOException {
        repository = loadDataBase();
        return repository;
    }

    @Override
    public List<ProductDTO> getProductsByTwoFilter(String param1, String value1, String param2, String value2) throws IOException {
        List<ProductDTO> arr = getProducts();
        List<ProductDTO> result = null;
        Predicate<ProductDTO> filter1 = Filter.getFilter(param1, value1);
        Predicate<ProductDTO> filter2 = Filter.getFilter(param2, value2);
        result = new ArrayList<ProductDTO>(arr.stream().filter(filter1.and(filter2)).collect(Collectors.toList()));
        return result;
    }

    @Override
    public List<ProductDTO> getProductsByOneFilter(String param1, String value1) throws IOException {
        List<ProductDTO> arr = getProducts();
        List<ProductDTO> result = null;
        Predicate<ProductDTO> filter1 = Filter.getFilter(param1, value1);
        result = new ArrayList<ProductDTO>(arr.stream().filter(filter1).collect(Collectors.toList()));
        return result;
    }
}

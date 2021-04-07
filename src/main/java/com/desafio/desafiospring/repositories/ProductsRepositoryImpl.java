package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.dtos.ProductResponseDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.exceptionsHandler.InvalidProduct;
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

    public ProductsRepositoryImpl() throws IOException {
        this.repository = loadDataBase();
    }

    private List<ProductDTO> loadDataBase() throws IOException {
        List<ProductDTO> products = new ArrayList();
        BufferedReader bufferReader = null;
        try {
            File file = ResourceUtils.getFile("classpath:dbProductos.csv");
            bufferReader = new BufferedReader(new FileReader(file));
            bufferReader.readLine();
            String line = bufferReader.readLine();

            while (line != null) {
                String[] attributes = line.split(",");

                Boolean shipping = false;
                if (attributes[6].toLowerCase(Locale.ROOT).equals("si")) shipping = true;

                // productId,name,category,brand,price,quantity,freeShipping,prestige
                ProductDTO productDTO = new ProductDTO(Integer.parseInt(attributes[0]), attributes[1], attributes[2],
                        attributes[3], attributes[4], Integer.parseInt(attributes[5]), shipping, attributes[7]
                );
                products.add(productDTO);
                line = bufferReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferReader != null) {
                try {
                    bufferReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return products;

    }

    @Override
    public List<ProductDTO> getProducts() {
        return repository;
    }

    @Override
    public List<ProductDTO> getProductsByFilter(String param1, String value1, String param2, String value2) throws DataNotFound, InvalidFilter {
        List<ProductDTO> arr = getProducts();
        List<ProductDTO> result = null;
        Predicate<ProductDTO> filter1 = Filter.getFilter(param1, value1);
        try {
            if (param2 != null) {
                Predicate<ProductDTO> filter2 = Filter.getFilter(param2, value2);
                result = new ArrayList(arr.stream().filter(filter1.and(filter2)).collect(Collectors.toList()));
            } else {
                result = new ArrayList(arr.stream().filter(filter1).collect(Collectors.toList()));
            }
        } catch (Exception e){
            throw new InvalidFilter("Los filtros no son correctos");
        }

        if (result.isEmpty()) throw new DataNotFound("No hay resultados para la búsqueda");
        return result;
    }

    @Override
    public ProductDTO getProductById(Integer productId) throws InvalidProduct {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getProductId().equals(productId)) return repository.get(i);
        }
        throw new InvalidProduct("No existe un producto con el ID " + productId);
    }

    @Override
    public void updateStock(ProductResponseDTO productResponseDTO) throws IOException, InvalidProduct {
        for (int i = 0; i < repository.size(); i++) {
            if (repository.get(i).getProductId().equals(productResponseDTO.getProductId())){
                Integer newStock = repository.get(i).getQuantity() - productResponseDTO.getQuantity();
                repository.get(i).setQuantity(newStock);
            }
        }
    }
}

package com.desafio.desafiospring.services;

import com.desafio.desafiospring.dtos.ProductDTO;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductDTO> getProducts(Map<String, String> params) throws IOException, InvalidFilter {
        // TODO separar un poco
        // Cuando no se ingresan parametros de busqueda
        if (params.isEmpty()){
            return productsRepository.getProducts();
        }

        // Cuando se ingresan mas de un parametro o el parametro cantidad
        if ((!params.containsKey("order") && params.size() > 2) ||
                (params.containsKey("order") && params.size() > 3) ||
                params.containsKey("quantity")) {
            throw new InvalidFilter("No puede aplicar mas de dos filtros ni filtrar por cantidad");
        }

        // Obtenemos el metodo de orden
        String order = null;

        if (params.containsKey("order")) {
            order = params.get("order");
            params.remove("order");
        }

        // Obtener parametros
        List<String> key = new ArrayList<>();
        List<String> value = new ArrayList<>();
        for(Map.Entry<String,String> entry:params.entrySet()) {
            key.add(entry.getKey());
            value.add(entry.getValue());
        }

        String param1 = null;
        String param2 = null;
        String value1 = null;
        String value2 = null;

        if (key.size() == 2){
            param1 = key.get(0);
            value1 = value.get(0);
            param2 = key.get(1);
            value2 = value.get(1);
        } else {
            param1 = key.get(0);
            value1 = value.get(0);
        }

        if (param2 != null) return productsRepository.getProductsByTwoFilter(param1, value1, param2, value2);
        else return productsRepository.getProductsByOneFilter(param1, value1);
    }

    @Override
    public Object getProductsOrdered(Map<String, String> params) throws IOException, InvalidFilter, DataNotFound {
        List<ProductDTO> result = getProducts(params);
        if (result.isEmpty()) throw new DataNotFound("No hay resultados para la busqueda");
        
        return result;
    }

    private List<ProductDTO> orderList(List<ProductDTO> result, String order) {
        return null;
    }
}

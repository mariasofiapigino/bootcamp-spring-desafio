package com.desafio.desafiospring.services;

import com.desafio.desafiospring.dtos.*;
import com.desafio.desafiospring.exceptionsHandler.DataNotFound;
import com.desafio.desafiospring.exceptionsHandler.InvalidFilter;
import com.desafio.desafiospring.exceptionsHandler.InvalidProduct;
import com.desafio.desafiospring.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class StoreServiceImpl implements StoreService{
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public List<ProductDTO> getProducts(Map<String, String> params) throws IOException, InvalidFilter, DataNotFound {
        // TODO separar un poco
        List<ProductDTO> result;

        // Cuando se ingresan mas de un filtro o el filtro cantidad
        if ((!params.containsKey("order") && params.size() > 2) ||
                (params.containsKey("order") && params.size() > 3) ||
                params.containsKey("quantity")) {
            throw new InvalidFilter("No puede aplicar mas de dos filtros ni filtrar por cantidad");
        }

        // Se obtiene el metodo de orden (en caso de existir) y se lo saca para dejar el array params solo con los filtros
        String order = null;

        if (params.containsKey("order")) {
            order = params.get("order");
            params.remove("order");
        }

        // Cuando no se ingresan parametros de busqueda
        if (params.isEmpty()){
            result = productsRepository.getProducts();
        } else {
            // Cuando si se ingresan parametros de busqueda, se obtienen los parametros
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

            // Se envian los parametros al metodo de filtrado
            result = productsRepository.getProductsByFilter(param1, value1, param2, value2);
        }
        // Se retorna el array ordenado
        return getProductsOrdered(result, order);
    }

    @Override
    public List<ProductDTO> getProductsOrdered(List<ProductDTO> products, String order) throws InvalidFilter {
        // Dependiendo del metodo de ordenamiento
        if (order == null) return products;
        if (order.equals("0")) return alphabetical(products, 0);
        if (order.equals("1")) return alphabetical(products, 1);
        if (order.equals("2")) return price(products, 2);
        if (order.equals("3")) return price(products, 3);
        throw new InvalidFilter("Metodo de ordenamiento invalido");
    }

    private List<ProductDTO> price(List<ProductDTO> products, int i) {
        Comparator<ProductDTO> c;
        if (i == 3) {
            c = Comparator.comparingInt(a -> Integer.parseInt(a.getPrice().replace("$", "").replace(".", "")));
        }
        else {
            c = (a, b) -> Integer.parseInt(b.getPrice().replace("$","").replace(".",""))
                    - Integer.parseInt(a.getPrice().replace("$","").replace(".",""));
        }
        products.sort(c);
        return products;
    }

    private List<ProductDTO> alphabetical(List<ProductDTO> products, Integer ascdesc) {
        Comparator<ProductDTO> c;
        if (ascdesc == 0) {
            c = Comparator.comparing(ProductDTO::getName);
        }
        else {
            c = (a, b) -> b.getName().compareTo(a.getName());
        }
        products.sort(c);
        return products;
    }

    @Override
    // Se calcula el total del ticket, pero si alguno de los datos enviados no coinciden con los datos almacenados
    // se envia una excepcion
    public Integer getTotalPrice(List<ProductResponseDTO> articles) throws IOException, InvalidProduct {
        Integer total = 0;
        for (int i = 0; i < articles.size(); i++) {
            ProductDTO productDTO = productsRepository.getProductById(articles.get(i).getProductId());
            if (!(productDTO.getName().toLowerCase(Locale.ROOT).equals(articles.get(i).getName().toLowerCase(Locale.ROOT))))
                throw new InvalidProduct("No existe un producto con ID " + articles.get(i).getProductId() + " y nombre " + articles.get(i).getName());
            if (!(productDTO.getBrand().toLowerCase(Locale.ROOT).equals(articles.get(i).getBrand().toLowerCase(Locale.ROOT))))
                throw new InvalidProduct("No existe un producto con ID " + articles.get(i).getProductId() + " y marca " + articles.get(i).getBrand());
            if (!(productDTO.getQuantity() >= articles.get(i).getQuantity()))
                throw new InvalidProduct("No hay suficiente stock del producto con ID " + articles.get(i).getProductId() + ", solo contamos con " + productDTO.getQuantity() + " unidades");
            total += Integer.parseInt(productDTO.getPrice().replace("$","").replace(".",""));
        }
        return total;
    }

    @Override
    // Se calcula el total del ticket y se forma el objeto ResponseLoadDTO
    public Object getTicket(ArticlesDTO articles) throws IOException, InvalidProduct {
        Integer total = getTotalPrice(articles.getArticles());
        TicketDTO ticketDTO = new TicketDTO(articles.getArticles(), total);
        StatusCodeDTO statusCodeDTO = new StatusCodeDTO(200, "La solicitud de compra se completó con éxito");
        ResponseLoadDTO responseLoadDTO = new ResponseLoadDTO(ticketDTO, statusCodeDTO);
        return responseLoadDTO;
    }

}

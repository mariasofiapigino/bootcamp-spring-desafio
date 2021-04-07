package com.desafio.desafiospring.utils;

import com.desafio.desafiospring.dtos.ProductDTO;

import java.util.Locale;
import java.util.function.Predicate;

public class Filter {
    public static Predicate<ProductDTO> getFilter(String param, String value){
        switch (param){
            case "name":
                return product -> product.getName().toLowerCase(Locale.ROOT).contains(value.toLowerCase(Locale.ROOT));
            case "brand":
                return product -> product.getBrand().toLowerCase(Locale.ROOT).contains(value.toLowerCase(Locale.ROOT));
            case "category":
                return product -> product.getCategory().toLowerCase(Locale.ROOT).contains(value.toLowerCase(Locale.ROOT));
            case "price":
                return product -> product.getPrice().replace(".","").replace("$","").equals(value);
            case "freeShipping":
                return product -> product.getFreeShipping().equals(Boolean.valueOf(value));
            case "prestige":
                return product -> String.valueOf(product.getPrestige().length()).equals(value);
            default:
                return null;
        }
    }
}

package com.desafio.desafiospring.dtos;

import lombok.Data;

@Data
public class ProductDTO {
    private Integer productId;
    private String name;
    private String category;
    private String brand;
    private String price;
    private Integer quantity;
    private String freeShipping;
    private String prestige;
}

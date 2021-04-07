package com.desafio.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Integer productId;
    private String name;
    private String category;
    private String brand;
    private String price;
    private Integer quantity;
    private Boolean freeShipping;
    private String prestige;
}

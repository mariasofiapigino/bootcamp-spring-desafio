package com.desafio.desafiospring.dtos;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private Integer productId;
    private String name;
    private  String brand;
    private Integer quantity;
}

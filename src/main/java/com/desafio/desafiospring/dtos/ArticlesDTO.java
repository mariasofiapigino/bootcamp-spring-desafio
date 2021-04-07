package com.desafio.desafiospring.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ArticlesDTO {
    private List<ProductResponseDTO> articles;
}

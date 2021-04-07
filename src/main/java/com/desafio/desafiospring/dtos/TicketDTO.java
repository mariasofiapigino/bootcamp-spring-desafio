package com.desafio.desafiospring.dtos;

import lombok.Data;

import java.util.List;

@Data
public class TicketDTO {
    private static Integer count = 1;
    private Integer id;
    private List<ProductResponseDTO> articles;
    private Integer total;

    public TicketDTO(List<ProductResponseDTO> articles, Integer total){
        this.id = count;
        this.articles = articles;
        this.total = total;
        count++;
    }
}

package com.desafio.desafiospring.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {
    List<TicketDTO> ticketList;
    Integer total;

    public ShoppingCartDTO(List<TicketDTO> tickets){
        this.ticketList = tickets;
        this.total = 0;
    }
}

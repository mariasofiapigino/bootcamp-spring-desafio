package com.desafio.desafiospring.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ShoppingCartDTO {
    List<TicketDTO> ticketList;
    Integer total;

    public ShoppingCartDTO(List<TicketDTO> tickets, Integer total){
        this.ticketList = tickets;
        this.total = total;
    }
}

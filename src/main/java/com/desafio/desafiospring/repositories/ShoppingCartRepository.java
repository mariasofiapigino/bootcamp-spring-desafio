package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.TicketDTO;

import java.util.List;

public interface ShoppingCartRepository {
    void addTicket(TicketDTO ticketDTO);

    Integer calculateTotal();

    List<TicketDTO> getTickets();
}

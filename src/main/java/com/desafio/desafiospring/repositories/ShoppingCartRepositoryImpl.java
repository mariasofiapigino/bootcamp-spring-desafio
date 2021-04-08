package com.desafio.desafiospring.repositories;

import com.desafio.desafiospring.dtos.TicketDTO;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShoppingCartRepositoryImpl implements ShoppingCartRepository{
    private static final List<TicketDTO> repository = new ArrayList<>();

    @Override
    public void addTicket(TicketDTO ticketDTO) {
        repository.add(ticketDTO);
    }

    @Override
    public Integer calculateTotal() {
        Integer total = 0;
        for (TicketDTO ticket:repository) {
            total += ticket.getTotal();
        }
        return total;
    }

    @Override
    public List<TicketDTO> getTickets() {
        return repository;
    }
}

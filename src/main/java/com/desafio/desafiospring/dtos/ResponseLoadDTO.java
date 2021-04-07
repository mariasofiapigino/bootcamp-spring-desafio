package com.desafio.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseLoadDTO {
    private TicketDTO ticket;
    private StatusCodeDTO statusCode;
}

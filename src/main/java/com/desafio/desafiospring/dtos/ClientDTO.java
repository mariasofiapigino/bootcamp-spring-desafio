package com.desafio.desafiospring.dtos;

import lombok.Data;

@Data
public class ClientDTO {
    private static Integer count = 1;
    private Integer id;
    private String name;
    private String lastName;
    private String province;
    private String email;


    public ClientDTO(String name, String lastName, String province, String email) {
        this.id = count;
        this.name = name;
        this.lastName = lastName;
        this.province = province;
        this.email = email;
        count++;
    }
}

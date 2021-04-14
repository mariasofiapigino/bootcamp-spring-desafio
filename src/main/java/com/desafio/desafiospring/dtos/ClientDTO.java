package com.desafio.desafiospring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private static Integer count = 1;
    private Integer id;
    private String name;
    private String lastName;
    private String province;
    private String email;


    public ClientDTO(String name, String lastName, String province, String email) {
        this.id = 0;
        this.name = name;
        this.lastName = lastName;
        this.province = province;
        this.email = email;
    }

    public void setId() {
        this.id = count;
        count++;
    }
}

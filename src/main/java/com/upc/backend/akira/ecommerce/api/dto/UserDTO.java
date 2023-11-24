package com.upc.backend.akira.ecommerce.api.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String numberCellphone;
    private String email;
    private String password;
    private String payment;

}

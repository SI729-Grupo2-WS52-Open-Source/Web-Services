package com.upc.backend.akira.ecommerce.api.dto.request;


import lombok.Data;

@Data
public class UserRequest {

    private String name;
    private String surname;
    private String numberCellphone;
    private String email;
    private String password;
    private String payment;
}

package com.upc.backend.akira.ecommerce.api.dto.response;

import lombok.Data;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String numberCellphone;
    private String email;
    private String password;
    private String payment;

}

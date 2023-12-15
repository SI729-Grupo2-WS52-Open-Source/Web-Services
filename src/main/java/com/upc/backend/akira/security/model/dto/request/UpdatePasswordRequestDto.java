package com.upc.backend.akira.security.model.dto.request;


import lombok.Data;

@Data
public class UpdatePasswordRequestDto {

    private String email;
    private String oldPassword;
    private String newPassword;

}

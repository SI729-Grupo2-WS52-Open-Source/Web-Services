package com.upc.backend.akira.security.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TokenResponseDto {
    private String token;
    private Long userId;
    private String name;
    private String surname;
    private String numberCellphone;
    private String email;
    private String password;

}

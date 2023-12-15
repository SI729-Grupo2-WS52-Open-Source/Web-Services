package com.upc.backend.akira.ecommerce.api.dto.response;

import com.upc.backend.akira.ecommerce.domain.model.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserResponse {

    private Long id;
    private String name;
    private String surname;
    private String numberCellphone;
    private String email;
    private String payment;
    private Set<Role> roles = new HashSet<>();


}

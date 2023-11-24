package com.upc.backend.akira.security.model.dto.response;

import com.upc.backend.akira.ecommerce.domain.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisteredUserResponseDto {
    private Long userId;
    private String fullName;
    private String username;
    private String email;
    private Set<Role> roles = new HashSet<>();
}
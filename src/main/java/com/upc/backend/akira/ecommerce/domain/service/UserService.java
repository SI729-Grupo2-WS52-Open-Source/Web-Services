package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.api.dto.UserDTO;
import com.upc.backend.akira.ecommerce.domain.model.entity.User;

public interface UserService {


    User createUser(UserDTO userDTO);

    User updateUser(Long id, UserDTO userDTO);

    void deleteUserById(Long id);
}
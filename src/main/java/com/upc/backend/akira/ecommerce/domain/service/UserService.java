package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.api.dto.request.UserRequest;
import com.upc.backend.akira.ecommerce.domain.model.entity.User;

public interface UserService {


    User createUser(UserRequest userRequest);

    User updateUser(Long id, UserRequest userRequest);

    void deleteUserById(Long id);
}
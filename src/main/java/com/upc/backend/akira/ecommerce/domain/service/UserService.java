package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.domain.model.entity.User;

public interface UserService {
    public abstract User createUser(User user);
    public abstract User updateUser(User user);
    public abstract void deleteUserById(Long user);
}

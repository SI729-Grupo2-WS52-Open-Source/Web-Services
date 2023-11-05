package com.upc.backend.akira.service;

import com.upc.backend.akira.model.User;

public interface UserService {
    public abstract User createUser(User user);
    public abstract User updateUser(User user);
    public abstract void deleteUserById(Long user);
}

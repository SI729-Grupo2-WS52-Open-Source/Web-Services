package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import com.upc.backend.akira.ecommerce.domain.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImplement implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) { return userRepository.save(user);}

    @Override
    public User updateUser(User user) { return userRepository.save(user);}

    @Override
    public void deleteUserById(Long userId) { userRepository.deleteById(userId);}
}

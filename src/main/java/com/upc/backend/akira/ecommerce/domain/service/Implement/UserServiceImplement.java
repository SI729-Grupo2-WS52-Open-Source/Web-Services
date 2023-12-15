package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.shared.exception.model.ValidationException;
import com.upc.backend.akira.ecommerce.api.dto.request.UserRequest;
import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import com.upc.backend.akira.ecommerce.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImplement implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImplement(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public User createUser(UserRequest userRequest) {
        User user = modelMapper.map(userRequest, User.class);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long id, UserRequest userRequest) {
        User existingUser = userRepository.findById(id).orElse(null);

        if (existingUser != null) {
            existingUser.setName(userRequest.getName());
            existingUser.setSurname(userRequest.getSurname());
            existingUser.setNumberCellphone(userRequest.getNumberCellphone());
            existingUser.setEmail(userRequest.getEmail());
            existingUser.setPayment(userRequest.getPayment());

            return userRepository.save(existingUser);
        } else {
            throw new ValidationException("No existe el usuario a modificar");
        }
    }

    @Override
    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }
}

package com.upc.backend.akira.ecommerce.api.rest;

import com.upc.backend.akira.ecommerce.api.dto.UserDTO;
import com.upc.backend.akira.ecommerce.api.dto.mapper.EcommerceMapper;
import com.upc.backend.akira.ecommerce.api.dto.request.UserRequest;
import com.upc.backend.akira.ecommerce.api.dto.response.UserResponse;
import com.upc.backend.akira.shared.exception.model.ValidationException;
import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import com.upc.backend.akira.ecommerce.domain.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class UserController {


    @Autowired
    private UserService userService;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserController(UserRepository userRepository, ModelMapper modelMapper){
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    //URL: "http://localhost:8080/users"
    //Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserResponse> userResponses = convertToUserResponses(users);
        return new ResponseEntity<>(userResponses, HttpStatus.OK);
    }


    //URL: "http://localhost:8080/users"
    //Method: POST
    @Transactional
    @PostMapping("/users")
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        validateUserRequest(userRequest);
        UserDTO userDTO = modelMapper.map(userRequest, UserDTO.class);
        User createdUser = userService.createUser(userDTO);
        UserResponse userResponse = modelMapper.map(createdUser, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.CREATED);
    }

    //URL: "http://localhost:8080/users/{id}"
    //Method: PUT
    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserRequest userRequest) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        validateUserRequest(userRequest);
        UserDTO userDTO = modelMapper.map(userRequest, UserDTO.class);
        User updatedUser = userService.updateUser(id, userDTO);
        UserResponse userResponse = modelMapper.map(updatedUser, UserResponse.class);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }


    //URL: "http://localhost:8080/users/{id}"
    //Method: DELETE
    @Transactional
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.deleteUserById(id);
        return ResponseEntity.noContent().build();
    }


    // URL: "http://localhost:8080/users/{id}"
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(modelMapper.map(user, UserResponse.class), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    // URL: "http://localhost:8080/login?email=${data.email}&password=${data.password}"
    // Method: GET
    @GetMapping("/login")
    public ResponseEntity<List<UserResponse>> userLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        User authenticatedUser = userRepository.findByEmailAndPassword(email, password);

        if (authenticatedUser != null) {
            List<UserResponse> userResponses = new ArrayList<>();
            userResponses.add(modelMapper.map(authenticatedUser, UserResponse.class));
            return ResponseEntity.ok(userResponses);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }
    }

    private void validateUserRequest(UserRequest userRequest){

        if (userRequest.getName() == null || userRequest.getName().isEmpty()) {
            throw new ValidationException("Username must not be empty");
        }
        if(userRequest.getName().length() > 20){
            throw new ValidationException("The username must not exceed 20 characters");
        }
        if (userRequest.getSurname() == null || userRequest.getSurname().isEmpty()) {
            throw new ValidationException("User last name must not be empty");
        }
        if(userRequest.getSurname().length() > 20){
            throw new ValidationException("The last name must not exceed 20 characters");
        }
        if(userRequest.getEmail() == null || userRequest.getEmail().isEmpty()){
            throw new ValidationException("The email must not be empty");
        }
        if(userRequest.getEmail().length() > 40){
            throw new ValidationException("The email must not exceed 40 characters");
        }
        if(userRequest.getPassword() == null || userRequest.getPassword().isEmpty()){
            throw new ValidationException("The password must not be empty");
        }
        if(userRequest.getPassword().length() > 30){
            throw new ValidationException("The password must not exceed 30 characters");
        }
    }

    private List<UserResponse> convertToUserResponses(List<User> users) {
        List<UserResponse> userResponses = new ArrayList<>();
        for (User user : users) {
            userResponses.add(modelMapper.map(user, UserResponse.class));
        }
        return userResponses;
    }

}

package com.upc.backend.akira.ecommerce.api.rest;

import com.upc.backend.akira.shared.exception.model.ValidationException;
import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import com.upc.backend.akira.ecommerce.domain.service.UserService;
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

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    //URL: "http://localhost:8080/users"
    //Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<List<User>>(userRepository.findAll(), HttpStatus.OK);
    }


    //URL: "http://localhost:8080/users"
    //Method: POST

    @Transactional
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User user){
        existsById(user);
        validateUser(user);
        return new ResponseEntity<User>(userService.createUser(user), HttpStatus.CREATED);
    }

    //URL: "http://localhost:8080/users/{id}"
    //Method: PUT

    @Transactional
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        if (!userRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        validateUser(user);
        user.setId(id);
        return new ResponseEntity<>(userService.updateUser(user), HttpStatus.OK);
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
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        Optional<User> user = userRepository.findById(id);

        if (userRepository.existsById(id)) {
            return new ResponseEntity<User>(user.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // URL: "http://localhost:8080/login?email=${data.email}&password=${data.password}"
    // Method: GET

    @GetMapping("/login")
    public ResponseEntity<List<User>> userLogin(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ) {
        User authenticatedUser = userRepository.findByEmailAndPassword(email, password);

        if (authenticatedUser != null) {
            List<User> userList = new ArrayList<>();
            userList.add(authenticatedUser);
            return ResponseEntity.ok(userList);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.emptyList());
        }
    }

    private void existsById(User user) {
        Long userId = user.getId();
        if (userId != null && userRepository.existsById(userId)) {
            throw new ValidationException("A user with that ID already exists");
        }
    }

    private void validateUser(User user){

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new ValidationException("Username must not be empty");
        }
        if(user.getName().length() > 20){
            throw new ValidationException("The username must not exceed 20 characters");
        }
        if (user.getSurname() == null || user.getSurname().isEmpty()) {
            throw new ValidationException("User last name must not be empty");
        }
        if(user.getSurname().length() > 20){
            throw new ValidationException("The last name must not exceed 20 characters");
        }
        if(user.getEmail() == null || user.getEmail().isEmpty()){
            throw new ValidationException("The email must not be empty");
        }
        if(user.getEmail().length() > 40){
            throw new ValidationException("The email must not exceed 40 characters");
        }
        if(user.getPassword() == null || user.getPassword().isEmpty()){
            throw new ValidationException("The password must not be empty");
        }
        if(user.getPassword().length() > 30){
            throw new ValidationException("The password must not exceed 30 characters");
        }
    }


}

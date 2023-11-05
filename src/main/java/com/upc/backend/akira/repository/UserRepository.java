package com.upc.backend.akira.repository;

import com.upc.backend.akira.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String userName);

    Optional<User> findById(User userId);

    User findByEmailAndPassword(String email, String password);

    boolean existsById(Long userId);

}
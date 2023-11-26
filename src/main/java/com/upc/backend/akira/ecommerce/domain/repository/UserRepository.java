package com.upc.backend.akira.ecommerce.domain.repository;

import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByName(String userName);

    Optional<User> findById(User userId);


    User findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);

    User findByEmail(String email);

    boolean existsByName(String name);


    boolean existsById(Long userId);

}
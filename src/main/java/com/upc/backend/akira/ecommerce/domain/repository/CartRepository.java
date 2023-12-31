package com.upc.backend.akira.ecommerce.domain.repository;

import com.upc.backend.akira.ecommerce.domain.model.entity.Cart;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {


    List<Cart> findByUserId(Long userId);


    Iterable<Cart> findAllByUserId(Long userId);


    void deleteById(Long Id);

    boolean existsById(Long Id);
}

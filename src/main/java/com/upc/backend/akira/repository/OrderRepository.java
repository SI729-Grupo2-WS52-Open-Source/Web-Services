package com.upc.backend.akira.repository;

import com.upc.backend.akira.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByEmail(String email);
    
    

    boolean existsById(Long OrderId);

    List<Order> findByUserId(Long userId);
}

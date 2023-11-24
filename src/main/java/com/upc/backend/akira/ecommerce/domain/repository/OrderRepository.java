package com.upc.backend.akira.ecommerce.domain.repository;

import com.upc.backend.akira.ecommerce.domain.model.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByEmail(String email);
    
    

    boolean existsById(Long OrderId);

    List<Order> findByUserId(Long userId);
}

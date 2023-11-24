package com.upc.backend.akira.ecommerce.domain.repository;

import com.upc.backend.akira.ecommerce.domain.model.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByName(String productName);

    @Query(value = "SELECT * FROM products ORDER BY RAND() LIMIT 6", nativeQuery = true)
    List<Product> findRandomTop6Products();
    List<Product> findTop10ByCategory(String category);

    List<Product> findByCategory(String category);

    List<Product> findByNameContainingIgnoreCase(String name);


    boolean existsById(Long productId);
}

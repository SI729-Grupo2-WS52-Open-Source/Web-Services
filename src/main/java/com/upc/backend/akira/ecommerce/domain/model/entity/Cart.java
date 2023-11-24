package com.upc.backend.akira.ecommerce.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "userId")
    private Long userId;

    // Fields from the Product entity
    @Column(name = "nameCategory", length = 40)
    private String nameCategory;

    @Column(name = "name", length = 40)
    private String name;

    @Column(name = "price")
    private Double price;

    @Column(name = "color", length = 20)
    private String color;

    @Column(name = "category", length = 40)
    private String category;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "image", length = 250)
    private String image;

    @Column(name = "productId")
    private Long productId;



}
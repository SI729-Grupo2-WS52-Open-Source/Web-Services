package com.upc.backend.akira.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_category", length = 40, nullable = false)
    private String nameCategory;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "color", length = 20, nullable = false)
    private String color;

    @Column(name = "category", length = 40, nullable = false)
    private String category;

    @Column(name = "description", length = 100, nullable = false)
    private String description;

    @Column(name = "image", length = 250, nullable = false)
    private String image;


}

package com.upc.backend.akira.ecommerce.api.dto;

import lombok.Data;

@Data
public class ProductDTO {

    private Long id;
    private String nameCategory;
    private String name;
    private Double price;
    private String color;
    private String category;
    private String description;
    private String image;

}

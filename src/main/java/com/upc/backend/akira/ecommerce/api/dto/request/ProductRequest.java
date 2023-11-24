package com.upc.backend.akira.ecommerce.api.dto.request;

import lombok.Data;

@Data
public class ProductRequest {

    private String nameCategory;
    private String name;
    private Double price;
    private String color;
    private String category;
    private String description;
    private String image;
}

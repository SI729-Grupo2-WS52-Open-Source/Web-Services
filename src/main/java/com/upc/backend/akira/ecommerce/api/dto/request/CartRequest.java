package com.upc.backend.akira.ecommerce.api.dto.request;

import lombok.Data;

@Data

public class CartRequest {

    private Long id;
    private int quantity;
    private Long userId;
    private String nameCategory;
    private String name;
    private Double price;
    private String color;
    private String category;
    private String description;
    private String image;
    private Long productId;

}

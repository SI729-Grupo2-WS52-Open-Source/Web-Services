package com.upc.backend.akira.ecommerce.api.dto.response;

import lombok.Data;

@Data
public class OrderResponse {

    private Long id;
    private String email;
    private String address;
    private String contact;
    private double totalPrice;
    private Long userId;
}

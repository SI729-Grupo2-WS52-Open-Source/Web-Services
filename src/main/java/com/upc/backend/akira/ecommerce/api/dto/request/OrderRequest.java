package com.upc.backend.akira.ecommerce.api.dto.request;

import lombok.Data;

@Data
public class OrderRequest {

    private String email;
    private String address;
    private String contact;
    private double totalPrice;
    private Long userId;
}

package com.upc.backend.akira.ecommerce.api.dto.response;

import lombok.Data;

@Data
public class ShippingResponse {

    private Long id;
    private String address;
    private String district;
    private String province;
    private String paymentMethod;
    private String linkedCard;
}

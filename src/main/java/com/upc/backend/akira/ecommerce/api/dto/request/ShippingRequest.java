package com.upc.backend.akira.ecommerce.api.dto.request;

import lombok.Data;

@Data
public class ShippingRequest {

    private String address;
    private String district;
    private String province;
    private String paymentMethod;
    private String linkedCard;
}

package com.upc.backend.akira.model;

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
@Table(name = "shipping")
public class Shipping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipping_id")
    private Long shippingId;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "district", length = 40, nullable = false)
    private String district;

    @Column(name = "province", length = 40, nullable = false)
    private String province;

    @Column(name = "payment_method", length = 40, nullable = false)
    private String paymentMethod;

    @Column(name = "linked_card", length = 40)
    private String linkedCard;

}

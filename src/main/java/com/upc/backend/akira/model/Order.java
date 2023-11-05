package com.upc.backend.akira.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @Column(name = "email", length = 40, nullable = false)
    private String email;

    @Column(name = "address", length = 100, nullable = false)
    private String address;

    @Column(name = "contact", length = 40, nullable = false)
    private String contact;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;


    @JoinColumn(name = "user_id")
    private Long userId;

}
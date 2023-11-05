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
@Table(name = "users")
public class User {

    @Column(name = "user_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 40, nullable = false)
    private String name;

    @Column(name = "surname", length = 40, nullable = false)
    private String surname;

    @Column(name = "numberCellphone", length = 40, nullable = false)
    private String numberCellphone;

    @Column(name = "email", length = 40, nullable = false)
    private String email;

    @Column(name = "password", length = 40, nullable = false)
    private String password;

    @Column(name = "payment", length = 40, nullable = true)
    private String payment;


}
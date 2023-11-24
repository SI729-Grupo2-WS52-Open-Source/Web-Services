package com.upc.backend.akira.ecommerce.domain.model.entity;

import com.upc.backend.akira.ecommerce.domain.model.enums.ERole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    //se tomará como un String los valores de este enum
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ERole name;
}

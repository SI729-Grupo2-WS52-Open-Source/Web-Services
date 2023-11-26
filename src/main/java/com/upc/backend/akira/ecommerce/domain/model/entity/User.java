package com.upc.backend.akira.ecommerce.domain.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "password",length = 500, nullable = false)
    private String password;

    @Column(name = "payment", length = 40, nullable = true)
    private String payment;

    /**
     * -Info: MUCHOS "usuarios" pueden tener MUCHOS "roles"
     * -JoinTable: la tabla intermediaria que se creará
     */
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id")
    )
    private Set<Role> roles = new HashSet<>();


}
package com.upc.backend.akira.ecommerce.domain.repository;

import com.upc.backend.akira.ecommerce.domain.model.entity.Shipping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShippingRepository extends JpaRepository<Shipping, Long> {

    List<Shipping> findByDistrictAndProvince(String district, String province);
    boolean existsById(Long shippingId);
}

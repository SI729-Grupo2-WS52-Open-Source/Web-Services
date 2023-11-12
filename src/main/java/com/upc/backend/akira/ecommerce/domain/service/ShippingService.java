package com.upc.backend.akira.ecommerce.domain.service;


import com.upc.backend.akira.ecommerce.domain.model.entity.Shipping;

import java.util.List;

public interface ShippingService {
    public abstract Shipping createShipping(Shipping shipping);

    Shipping getShippingById(Long id);

    Shipping updateShipping(Long id, Shipping shipping);

    List<Shipping> getAllShippings();

    boolean deleteShipping(Long id);

    public List<Shipping> getAllShippingsWithDetails();
}

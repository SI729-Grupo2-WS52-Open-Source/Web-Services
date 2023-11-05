package com.upc.backend.akira.service;


import com.upc.backend.akira.model.Shipping;

import java.util.List;

public interface ShippingService {
    public abstract Shipping createShipping(Shipping shipping);

    Shipping getShippingById(Long id);

    Shipping updateShipping(Long id, Shipping shipping);

    List<Shipping> getAllShippings();

    boolean deleteShipping(Long id);

    public List<Shipping> getAllShippingsWithDetails();
}

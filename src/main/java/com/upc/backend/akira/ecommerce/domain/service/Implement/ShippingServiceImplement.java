package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.domain.model.entity.Shipping;
import com.upc.backend.akira.ecommerce.domain.repository.ShippingRepository;
import com.upc.backend.akira.ecommerce.domain.service.ShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShippingServiceImplement implements ShippingService {

    private final ShippingRepository shippingRepository;

    @Autowired
    public ShippingServiceImplement(ShippingRepository shippingRepository) {
        this.shippingRepository = shippingRepository;
    }

    @Override
    public Shipping createShipping(Shipping shipping) {
        return shippingRepository.save(shipping);
    }

    @Override
    public Shipping getShippingById(Long id) {
        Optional<Shipping> optionalShipping = shippingRepository.findById(id);
        return optionalShipping.orElse(null);
    }

    @Override
    public Shipping updateShipping(Long id, Shipping newShipping) {
        Optional<Shipping> existingShipping = shippingRepository.findById(id);
        if (existingShipping.isPresent()) {
            Shipping currentShipping = existingShipping.get();
            currentShipping.setAddress(newShipping.getAddress());
            currentShipping.setDistrict(newShipping.getDistrict());
            currentShipping.setProvince(newShipping.getProvince());
            currentShipping.setPaymentMethod(newShipping.getPaymentMethod());
            currentShipping.setLinkedCard(newShipping.getLinkedCard());

            return shippingRepository.save(currentShipping);
        }
        return null;
    }

    @Override
    public List<Shipping> getAllShippings() {
        List<Shipping> shippings = shippingRepository.findAll();

        for (Shipping shipping : shippings) {

        }

        return shippings;
    }
    @Override
    public List<Shipping> getAllShippingsWithDetails() {
        List<Shipping> shippings = shippingRepository.findAll();
        for (Shipping shipping : shippings) {

            System.out.println("Detalles del envío: " + shipping.getAddress() + ", " + shipping.getDistrict() + ", " + shipping.getProvince());

        }
        return shippings;
    }

    @Override
    public boolean deleteShipping(Long id) {
        Optional<Shipping> existingShipping = shippingRepository.findById(id);
        if (existingShipping.isPresent()) {
            shippingRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

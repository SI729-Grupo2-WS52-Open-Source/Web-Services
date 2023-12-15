package com.upc.backend.akira.ecommerce.api.rest;

import com.upc.backend.akira.ecommerce.domain.model.entity.Shipping;
import com.upc.backend.akira.ecommerce.domain.service.ShippingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Tag(name = "Shipping Controller")

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @Operation(summary = "Obtiene datos de envio de un usuario ")
    @GetMapping("/{id}")
    public ResponseEntity<Shipping> getShippingById(@PathVariable Long id) {
        Shipping shipping = shippingService.getShippingById(id);
        if (shipping != null) {
            return new ResponseEntity<>(shipping, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Obtiene los datos de envio de todos los usuarios")
    @GetMapping
    public ResponseEntity<List<Shipping>> getAllShippings() {
        List<Shipping> shippings = shippingService.getAllShippings();
        return new ResponseEntity<>(shippings, HttpStatus.OK);
    }

    @Operation(summary = "Registra los datos de envio")
    @PostMapping
    public ResponseEntity<Shipping> createShipping(@RequestBody Shipping shipping) {
        Shipping createdShipping = shippingService.createShipping(shipping);
        return new ResponseEntity<>(createdShipping, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza los datos de envio")
    @PutMapping("/{id}")
    public ResponseEntity<Shipping> updateShipping(@PathVariable Long id, @RequestBody Shipping updatedShipping) {
        Shipping existingShipping = shippingService.getShippingById(id);
        if (existingShipping != null) {
            updatedShipping.setShippingId(existingShipping.getShippingId()); // Asegurando que el ID no se modifique
            Shipping savedShipping = shippingService.updateShipping(id, updatedShipping);
            return new ResponseEntity<>(savedShipping, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @Operation(summary = "Elimina los datos de envio de un usuario")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipping(@PathVariable Long id) {
        boolean deleted = shippingService.deleteShipping(id);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

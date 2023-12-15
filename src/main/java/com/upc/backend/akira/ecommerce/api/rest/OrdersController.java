package com.upc.backend.akira.ecommerce.api.rest;


import com.upc.backend.akira.ecommerce.domain.model.entity.Order;
import com.upc.backend.akira.ecommerce.domain.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Orders Controller")
@RestController
@RequestMapping("/")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Operation(summary = "Registra una nueva orden")
    @PostMapping("/orders")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order newOrder = orderService.createOrder(order);
        return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
    }

    @Operation(summary = "Obtiene todas las órdenes de un usuario")
    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getOrdersByUserId(@RequestParam Long userId) {
        List<Order> userOrders = orderService.getOrdersByUserId(userId);
        return new ResponseEntity<>(userOrders, HttpStatus.OK);
    }

    @Operation(summary = "Elimina una orden")
    @DeleteMapping("/orders/{orderId}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrder(orderId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

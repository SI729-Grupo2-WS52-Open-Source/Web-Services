package com.upc.backend.akira.service;

import com.upc.backend.akira.model.Order;

import java.util.List;

public interface OrderService {

    public abstract Order createOrder(Order order);

    List<Order> getOrdersByUserId(Long userId);

    void deleteOrder(Long orderId);
}

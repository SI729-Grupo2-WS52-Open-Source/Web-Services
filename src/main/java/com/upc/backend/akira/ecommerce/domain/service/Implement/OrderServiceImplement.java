package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.domain.model.entity.Order;
import com.upc.backend.akira.ecommerce.domain.repository.OrderRepository;
import com.upc.backend.akira.ecommerce.domain.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImplement implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order){ return orderRepository.save(order);}

    @Override
    public List<Order> getOrdersByUserId(Long userId) {
        return orderRepository.findByUserId(userId);
    }

    @Override
    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }

}

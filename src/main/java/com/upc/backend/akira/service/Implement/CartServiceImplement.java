package com.upc.backend.akira.service.Implement;

import com.upc.backend.akira.model.Cart;
import com.upc.backend.akira.model.Product;
import com.upc.backend.akira.repository.CartRepository;
import com.upc.backend.akira.repository.ProductRepository;
import com.upc.backend.akira.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CartServiceImplement implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public CartServiceImplement(CartRepository cartRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(Long cartId) {
        cartRepository.deleteById(cartId);
    }

    @Override
    public Iterable<Cart> getCartByUserId(Long userId) {
        return cartRepository.findAllByUserId(userId);
    }
}
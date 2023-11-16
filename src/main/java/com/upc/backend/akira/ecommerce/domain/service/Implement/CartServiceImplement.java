package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.api.dto.CartDTO;
import com.upc.backend.akira.ecommerce.domain.model.entity.Cart;
import com.upc.backend.akira.ecommerce.domain.repository.CartRepository;
import com.upc.backend.akira.ecommerce.domain.repository.ProductRepository;
import com.upc.backend.akira.ecommerce.domain.service.CartService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CartServiceImplement implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CartServiceImplement(CartRepository cartRepository, ProductRepository productRepository, ModelMapper modelMapper) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Cart createCart(Cart cart) {
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
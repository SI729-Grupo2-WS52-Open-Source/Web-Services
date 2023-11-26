package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.api.dto.CartDTO;
import com.upc.backend.akira.ecommerce.domain.model.entity.Cart;

public interface CartService {

    public abstract Cart createCart(Cart cart);

    public abstract void  removeFromCart(Long cartId);

    public abstract Iterable<Cart> getCartByUserId(Long userId);

}

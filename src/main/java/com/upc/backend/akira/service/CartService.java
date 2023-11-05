package com.upc.backend.akira.service;

import com.upc.backend.akira.model.Cart;
import com.upc.backend.akira.model.Product;

public interface CartService {

    public abstract Cart createCart(Cart cart);

    public abstract Cart addToCart(Cart cart);

    public abstract void  removeFromCart(Long cartId);

    public abstract Iterable<Cart> getCartByUserId(Long userId);

}

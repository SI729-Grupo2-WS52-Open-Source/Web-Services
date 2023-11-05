package com.upc.backend.akira.controller;

import com.upc.backend.akira.model.Cart;
import com.upc.backend.akira.model.Product;
import com.upc.backend.akira.model.User;
import com.upc.backend.akira.repository.CartRepository;
import com.upc.backend.akira.repository.UserRepository;
import com.upc.backend.akira.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    private final CartRepository cartRepository;

    public CartController(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @GetMapping("/cart")
    public ResponseEntity<Iterable<Cart>> getCartByUserId(@RequestParam Long userId) {
        Iterable<Cart> userCart = cartService.getCartByUserId(userId);
        return new ResponseEntity<>(userCart, HttpStatus.OK);
    }


    @Transactional
    @PostMapping("/cart")
    public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
        // existsById(cart);

        return new ResponseEntity<>(cartService.createCart(cart), HttpStatus.CREATED);
    }

    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        Cart addedCart = cartService.addToCart(cart);
        return new ResponseEntity<>(addedCart, HttpStatus.CREATED);
    }
}
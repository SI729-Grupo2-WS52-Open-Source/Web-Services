package com.upc.backend.akira.ecommerce.api.rest;

import com.upc.backend.akira.ecommerce.api.dto.request.CartRequest;
import com.upc.backend.akira.ecommerce.api.dto.response.CartResponse;
import com.upc.backend.akira.ecommerce.domain.model.entity.Cart;
import com.upc.backend.akira.ecommerce.domain.repository.CartRepository;
import com.upc.backend.akira.ecommerce.domain.repository.UserRepository;
import com.upc.backend.akira.ecommerce.domain.service.CartService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Tag(name = "Carts Controller")
@RestController
@RequestMapping("/")
public class CartsController {

    @Autowired
    private CartService cartService;
    @Autowired

    private UserRepository userRepository;
    @Autowired

    private final CartRepository cartRepository;
    @Autowired

    private final ModelMapper modelMapper;

    public CartsController(UserRepository userRepository, CartRepository cartRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.cartRepository = cartRepository;
        this.modelMapper = modelMapper;
    }


    @Operation(summary = "Obtiene un carrito")
    @GetMapping("/cart")
    public ResponseEntity<Iterable<CartResponse>> getCartByUserId(@RequestParam Long userId) {
        Iterable<Cart> userCart = cartService.getCartByUserId(userId);
        Iterable<CartResponse> cartResponses = mapCartListToCartResponseList(userCart);
        return new ResponseEntity<>(cartResponses, HttpStatus.OK);
    }


    @Operation(summary = "Registra un carrito")
    @PostMapping("/cart")
    public CartResponse createCart(@RequestBody CartRequest cartRequest) {
        Cart cart = modelMapper.map(cartRequest, Cart.class);
        Cart createdCart = cartService.createCart(cart);
        return modelMapper.map(createdCart, CartResponse.class);
    }


    @Operation(summary = "Elimina un carrito")
    @DeleteMapping("/cart/{cartId}")
    public ResponseEntity<Void> removeFromCart(@PathVariable Long cartId) {
        cartService.removeFromCart(cartId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Iterable<CartResponse> mapCartListToCartResponseList(Iterable<Cart> carts) {
        List<CartResponse> cartResponses = new ArrayList<>();
        for (Cart cart : carts) {
            cartResponses.add(modelMapper.map(cart, CartResponse.class));
        }
        return cartResponses;
    }


}

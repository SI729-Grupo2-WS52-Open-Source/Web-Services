package com.upc.backend.akira.ecommerce.api.dto.mapper;

import com.upc.backend.akira.ecommerce.api.dto.UserDTO;
import com.upc.backend.akira.ecommerce.api.dto.request.CartRequest;
import com.upc.backend.akira.ecommerce.api.dto.request.ProductRequest;
import com.upc.backend.akira.ecommerce.api.dto.request.UserRequest;
import com.upc.backend.akira.ecommerce.api.dto.response.CartResponse;
import com.upc.backend.akira.ecommerce.api.dto.response.ProductResponse;
import com.upc.backend.akira.ecommerce.api.dto.response.UserResponse;
import com.upc.backend.akira.ecommerce.domain.model.entity.Cart;
import com.upc.backend.akira.ecommerce.domain.model.entity.Product;
import com.upc.backend.akira.ecommerce.domain.model.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EcommerceMapper {
    EcommerceMapper INSTANCE= Mappers.getMapper(EcommerceMapper.class);

    //User
    UserDTO UserRequestToUser(UserRequest userRequest);
    UserResponse UserToUserResponse(User user);

    //Product
    Product ProductRequestToProduct(ProductRequest productRequest);
    ProductResponse ProductToProductResponse(Product product);

    //Cart
    Cart CartRequestToCart(CartRequest cartRequest);
    CartResponse CartToCartResponse(Cart cart);
}

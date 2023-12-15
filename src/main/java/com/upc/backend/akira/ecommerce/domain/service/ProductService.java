package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.api.dto.request.ProductRequest;
import com.upc.backend.akira.ecommerce.domain.model.entity.Product;

public interface ProductService {

    public abstract Product createProduct(ProductRequest productRequest);
    public abstract Product updateProduct(ProductRequest productRequest);
    public abstract void deleteProduct(Long product);

}

package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.domain.model.entity.Product;

public interface ProductService {

    public abstract Product createProduct(Product product);
    public abstract Product updateProduct(Product product);
    public abstract void  deleteProduct(Long product);

}

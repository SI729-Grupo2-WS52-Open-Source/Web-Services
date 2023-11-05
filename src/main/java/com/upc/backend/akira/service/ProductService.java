package com.upc.backend.akira.service;

import com.upc.backend.akira.model.Product;

public interface ProductService {

    public abstract Product createProduct(Product product);
    public abstract Product updateProduct(Product product);
    public abstract void  deleteProduct(Long product);

}

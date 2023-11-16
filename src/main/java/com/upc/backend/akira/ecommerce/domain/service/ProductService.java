package com.upc.backend.akira.ecommerce.domain.service;

import com.upc.backend.akira.ecommerce.api.dto.ProductDTO;
import com.upc.backend.akira.ecommerce.domain.model.entity.Product;

public interface ProductService {

    public abstract Product createProduct(ProductDTO productDTO);
    public abstract Product updateProduct(ProductDTO productDTO);
    public abstract void deleteProduct(Long product);

}

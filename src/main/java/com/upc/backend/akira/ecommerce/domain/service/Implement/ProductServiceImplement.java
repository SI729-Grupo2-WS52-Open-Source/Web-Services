package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.domain.model.entity.Product;
import com.upc.backend.akira.ecommerce.domain.repository.ProductRepository;
import com.upc.backend.akira.ecommerce.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product createProduct(Product product){ return productRepository.save(product);}

    @Override
    public Product updateProduct(Product product){ return productRepository.save(product); }

    @Override
    public void deleteProduct(Long product){ productRepository.deleteById(product); }
}

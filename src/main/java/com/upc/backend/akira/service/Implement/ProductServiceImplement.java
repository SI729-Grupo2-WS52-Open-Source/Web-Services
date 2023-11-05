package com.upc.backend.akira.service.Implement;

import com.upc.backend.akira.model.Product;
import com.upc.backend.akira.repository.ProductRepository;
import com.upc.backend.akira.service.ProductService;
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

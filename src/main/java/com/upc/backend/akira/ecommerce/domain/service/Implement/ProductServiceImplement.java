package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.api.dto.request.ProductRequest;
import com.upc.backend.akira.ecommerce.domain.model.entity.Product;
import com.upc.backend.akira.ecommerce.domain.repository.ProductRepository;
import com.upc.backend.akira.ecommerce.domain.service.ProductService;
import com.upc.backend.akira.shared.exception.model.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplement implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImplement(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Product createProduct(ProductRequest productRequest) {
        Product product = modelMapper.map(productRequest, Product.class);
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(ProductRequest productRequest) {  // Cambio de ProductDTO a ProductRequest
        Product existingProduct = productRepository.findById(productRequest.getId()).orElse(null);

        if (existingProduct != null) {
            modelMapper.map(productRequest, existingProduct);
            return productRepository.save(existingProduct);
        } else {
            throw new ValidationException("No existe el producto a modificar");
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}

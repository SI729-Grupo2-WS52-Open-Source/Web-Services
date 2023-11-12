package com.upc.backend.akira.ecommerce.domain.service.Implement;

import com.upc.backend.akira.ecommerce.api.dto.ProductDTO;
import com.upc.backend.akira.ecommerce.domain.model.entity.Product;
import com.upc.backend.akira.ecommerce.domain.repository.ProductRepository;
import com.upc.backend.akira.ecommerce.domain.service.ProductService;
import com.upc.backend.akira.shared.exception.model.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImplement implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final ModelMapper modelMapper;

    public ProductServiceImplement(ProductRepository productRepository, ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }


    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        return productRepository.save(product);
    }
    @Override
    public Product updateProduct(ProductDTO productDTO){

        Product existingProduct = productRepository.findById(productDTO.getId()).orElse(null);
        if (existingProduct != null) {
            modelMapper.map(productDTO, existingProduct);
            return productRepository.save(existingProduct);
        } else {
            throw new ValidationException("No existe el producto a modificar");        }
    }

    @Override
    public void deleteProduct(Long product){ productRepository.deleteById(product); }
}

package com.upc.backend.akira.controller;


import com.upc.backend.akira.exception.ValidationException;
import com.upc.backend.akira.model.Product;
import com.upc.backend.akira.repository.ProductRepository;
import com.upc.backend.akira.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class ProductController {

    @Autowired
    private ProductService productService;

    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository){ this.productRepository = productRepository;}

    //URL: "http://localhost:8080/products"
    //Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<List<Product>>(productRepository.findAll(), HttpStatus.OK);
    }

    //URL: "http://localhost:8080/products"
    //Method: POST

    @Transactional
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        existsById(product);
        ValidateProduct(product);

        return new ResponseEntity<Product>(productService.createProduct(product), HttpStatus.CREATED);
    }


    //URL: "http://localhost:8080/products/{id}"
    //Method: PUT

    @Transactional
    @PutMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product){
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ValidateProduct(product);
        product.setId(id);
        return new ResponseEntity<>(productService.updateProduct(product),HttpStatus.OK);
    }


    //URL: "http://localhost:8080/products/{id}"
    //Method: DELETE

    @Transactional
    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (!productRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

    // URL: "http://localhost:8080/products/{id}"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Optional<Product> product = productRepository.findById(id);

        if (productRepository.existsById(id)) {
            return new ResponseEntity<Product>(product.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // URL: "http://localhost:8080/products/anime"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/anime")
    public ResponseEntity<List<Product>> getAnimeProducts() {
        List<Product> animeProducts = productRepository.findByCategory("Anime");
        return new ResponseEntity<>(animeProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/kpop"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/kpop")
    public ResponseEntity<List<Product>> getKpopProducts() {
        List<Product> kpopProducts = productRepository.findByCategory("KPOP");
        return new ResponseEntity<>(kpopProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/lectura"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/lectura")
    public ResponseEntity<List<Product>> getLecturaProducts() {
        List<Product> lecturaProducts = productRepository.findByCategory("Lectura");
        return new ResponseEntity<>(lecturaProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/popular"
    // Method: GET
    @Transactional(readOnly = true)
    @GetMapping("/products/popular")
    public ResponseEntity<List<Product>> getPopularProducts(){
        List<Product> popularProducts = productRepository.findRandomTop6Products();
        return new ResponseEntity<>(popularProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/popular/anime"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/popular/anime")
    public ResponseEntity<List<Product>> getPopularAnimeProducts() {
        List<Product> popularAnimeProducts = productRepository.findTop10ByCategory("Anime");
        return new ResponseEntity<>(popularAnimeProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/popular/kpop"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/popular/kpop")
    public ResponseEntity<List<Product>> getPopularKpopProducts() {
        List<Product> popularKpopProducts = productRepository.findTop10ByCategory("KPOP");
        return new ResponseEntity<>(popularKpopProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/popular/lectura"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/popular/lectura")
    public ResponseEntity<List<Product>> getPopularLecturaProducts() {
        List<Product> popularLecturaProducts = productRepository.findTop10ByCategory("Lectura");
        return new ResponseEntity<>(popularLecturaProducts, HttpStatus.OK);
    }

    // URL: "http://localhost:8080/products/search"
    // Method: GET

    @Transactional(readOnly = true)
    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> searchProducts(@RequestParam String query) {
        List<Product> foundProducts = productRepository.findByNameContainingIgnoreCase(query);

        if (foundProducts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(foundProducts, HttpStatus.OK);
        }
    }

    private void existsById(Product product) {
        Long productId = product.getId();
        if (productId != null && productRepository.existsById(productId)) {
            throw new ValidationException("A product with that ID already exists");
        }
    }
    private void ValidateProduct(Product product){

        if (product.getNameCategory() == null || product.getNameCategory().isEmpty()) {
            throw new ValidationException("NameCategory product must not be empty");
        }
        if(product.getNameCategory().length() > 40){
            throw new ValidationException("NameCategory product must not exceed 40 characters");
        }
        if (product.getName() == null || product.getName().isEmpty()) {
            throw new ValidationException("Name product must not be empty");
        }
        if(product.getName().length() > 40){
            throw new ValidationException("Name product must not exceed 40 characters");
        }
        if (product.getCategory() == null || product.getCategory().isEmpty()) {
            throw new ValidationException("Category product must not be empty");
        }
        if(product.getCategory().length() > 40){
            throw new ValidationException("Category product must not exceed 40 characters");
        }
        if (product.getPrice() == null) {
            throw new ValidationException("Price product must not be empty");
        }
        if (product.getColor() == null || product.getColor().isEmpty()) {
            throw new ValidationException("Color product must not be empty");
        }
        if(product.getColor().length() > 20){
            throw new ValidationException("Color product must not exceed 20 characters");
        }
        if (product.getDescription() == null || product.getDescription().isEmpty()) {
            throw new ValidationException("Description product must not be empty");
        }
        if(product.getDescription().length() > 100){
            throw new ValidationException("Description product must not exceed 100 characters");
        }
        if (product.getImage() == null || product.getImage().isEmpty()) {
            throw new ValidationException("Image product must not be empty");
        }
        if(product.getImage().length() > 250){
            throw new ValidationException("Image product must not exceed 100 characters");
        }


    }

}

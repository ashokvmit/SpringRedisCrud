package com.redis.controller;

import com.redis.dto.ProductSearchRequest;
import com.redis.entity.Product;
import com.redis.serviceimpl.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // Endpoint to fetch all products
    @Cacheable(value = "products")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // Endpoint to fetch a product by ID
    @Cacheable(value = "product", key = "#id")
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        Optional<Product> product = productService.getProductById(id);
        return product.orElse(null);
    }

    // Endpoint to search products based on criteria in request body
    @Cacheable(value = "searchProducts", keyGenerator = "customKeyGenerator")
    @PostMapping("/search")
    public List<Product> searchProducts(@RequestBody ProductSearchRequest request) {
        return productService.searchProducts(request);
    }

    // Endpoint to create a new product
    @CacheEvict(value = {"products", "searchProducts"}, allEntries = true)
    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        return productService.createProduct(product);
    }

    // Endpoint to update an existing product
    @CachePut(value = "product", key = "#id")
    @CacheEvict(value = {"products", "searchProducts"}, allEntries = true)
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
        return productService.updateProduct(id, productDetails);
    }

    // Endpoint to delete a product by ID
    @CacheEvict(value = {"products", "product", "searchProducts"}, key = "#id", allEntries = true)
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

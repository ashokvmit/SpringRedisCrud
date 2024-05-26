package com.redis.serviceimpl;

import com.redis.dto.ProductSearchRequest;
import com.redis.entity.Product;
import com.redis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return productRepository.findById(id);
    }

    public List<Product> searchProducts(ProductSearchRequest request) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Example search logic, adjust as necessary for your specific requirements
        if (request.getName() != null && request.getMinPrice() != null && request.getMaxPrice() != null) {
            return productRepository.findByNameAndPriceBetween(request.getName(), request.getMinPrice(), request.getMaxPrice());
        } else if (request.getName() != null) {
            return productRepository.findByName(request.getName());
        } else if (request.getMinPrice() != null && request.getMaxPrice() != null) {
            return productRepository.findByPriceBetween(request.getMinPrice(), request.getMaxPrice());
        } else {
            return productRepository.findAll();
        }
    }

    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setName(productDetails.getName());
        product.setPrice(productDetails.getPrice());
        product.setDescription(productDetails.getDescription());
        product.setCategory(productDetails.getCategory());
        product.setStockQuantity(productDetails.getStockQuantity());
        product.setCreatedDate(productDetails.getCreatedDate());
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}

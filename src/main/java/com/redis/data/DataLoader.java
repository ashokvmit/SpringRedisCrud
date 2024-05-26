package com.redis.data;

import com.redis.entity.Product;
import com.redis.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void run(String... args) throws Exception {
        // List of possible categories
        List<String> categories = List.of("Category 1", "Category 2", "Category 3", "Category 4", "Category 5");

        // Creating 100 sample products with random categories
        Random random = new Random();

        for (int i = 1; i <= 100; i++) {
            String name = "Product " + i;
            double price = 50 + (200 - 50) * random.nextDouble();
            price = Double.parseDouble(String.format("%.2f", price)); // Random price between 50 and 200
            String description = "Description " + i;
            String category = categories.get(random.nextInt(categories.size())); // Random category
            int stockQuantity = random.nextInt(100); // Random stock quantity between 0 and 99
            Date createdDate = new Date();

            Product product = new Product(name, price, description, category, stockQuantity, createdDate);
            productRepository.save(product);
        }

        System.out.println("Sample data loaded!");
    }
}

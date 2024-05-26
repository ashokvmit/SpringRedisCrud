package com.redis.repository;

import com.redis.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Custom query methods based on search criteria

    List<Product> findByName(String name);

    List<Product> findByPriceBetween(Double minPrice, Double maxPrice);

    List<Product> findByNameAndPriceBetween(String name, Double minPrice, Double maxPrice);

}

package com.redis.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Product implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int stockQuantity;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Product() {
        // Default constructor for JPA
    }

    // Custom constructor for convenience
    public Product(String name, double price, String description, String category, int stockQuantity, Date createdDate) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.createdDate = createdDate;
    }
}

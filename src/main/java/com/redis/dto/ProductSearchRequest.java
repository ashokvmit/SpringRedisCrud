package com.redis.dto;

import lombok.Data;

@Data
public class ProductSearchRequest {
    private String name;
    private Double minPrice;
    private Double maxPrice;
}

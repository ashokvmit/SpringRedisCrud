package com.redis.utils;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.StringJoiner;

@Component("customKeyGenerator")
public class CustomKeyGenerator implements KeyGenerator {

    @Override
    public Object generate(Object target, Method method, Object... params) {
        StringJoiner joiner = new StringJoiner("_");
        joiner.add(method.getName());
        for (Object param : params) {
            if (param != null) {
                if (param instanceof com.redis.dto.ProductSearchRequest) {
                    com.redis.dto.ProductSearchRequest request = (com.redis.dto.ProductSearchRequest) param;
                    joiner.add(request.getName() != null ? request.getName() : "null");
                    joiner.add(request.getMinPrice() != null ? request.getMinPrice().toString() : "null");
                    joiner.add(request.getMaxPrice() != null ? request.getMaxPrice().toString() : "null");
                } else {
                    joiner.add(param.toString());
                }
            } else {
                joiner.add("null");
            }
        }
        return joiner.toString();
    }
}

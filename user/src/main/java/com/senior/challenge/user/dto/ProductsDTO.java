package com.senior.challenge.user.dto;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

public class ProductsDTO {
    private Map<String, Object> products = new LinkedHashMap<>();

    @JsonAnySetter
    void setProduct(String key, Object value) {
        products.put(key, value);
    }

    public Map<String, Object> getProducts(){
        return this.products;
    }
}

package com.example.service;

import com.example.domain.model.CreateProductRequest;
import com.example.domain.model.Product;

public interface ProductService {
    Product save(CreateProductRequest createProductRequest);
}

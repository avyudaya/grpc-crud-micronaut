package com.example.domain.port;

import com.example.domain.model.CreateProductRequest;
import com.example.domain.model.Product;

public interface ProductCreator {
    Product save(CreateProductRequest request);
}

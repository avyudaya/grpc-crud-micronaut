package com.example.service;

import com.example.domain.model.CreateProductRequest;
import com.example.domain.model.Product;
import com.example.domain.port.ProductCreator;
import io.micronaut.context.annotation.Primary;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
@Primary
class ProductServiceInteractor implements ProductService{

    @Inject
    ProductCreator productCreator;

    @Override
    public Product save(CreateProductRequest createProductRequest) {

        if(createProductRequest.getPrice() < 200) {
            throw new IllegalArgumentException("You can not add the product with price less than 200");
        }
        // validate req
        return productCreator.save(createProductRequest);
    }
}

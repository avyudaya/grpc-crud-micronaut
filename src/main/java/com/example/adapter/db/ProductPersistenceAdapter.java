package com.example.adapter.db;

import com.example.domain.model.CreateProductRequest;
import com.example.domain.model.Product;
import com.example.domain.port.ProductCreator;
import com.example.domain.port.ProductFinder;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

@Singleton
class ProductPersistenceAdapter implements ProductCreator, ProductFinder {
    @Inject private ProductStore productStore;

    @Override
    public Product save(CreateProductRequest request) {
        var productEntity = ProductEntity.from(request);
        return productStore.save(productEntity).toProduct();
    }
}

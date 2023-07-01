package com.example.api.endpoints;

import jakarta.inject.Singleton;
import lombok.Getter;

@Singleton
@Getter
public class ProductEndpoints {
    private final ProductEndpoint productEndpoint;

    public ProductEndpoints(ProductEndpoint productEndpoint) {
        this.productEndpoint = productEndpoint;
    }
}

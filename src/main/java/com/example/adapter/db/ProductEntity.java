package com.example.adapter.db;


import com.example.domain.model.CreateProductRequest;
import com.example.domain.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "product")
@Builder
class ProductEntity {
    @Id
    private String id;
    private String name;
    private Double price;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public static ProductEntity from(CreateProductRequest createProductRequest) {
        return ProductEntity.builder()
                .id(UUID.randomUUID().toString())
                .name(createProductRequest.getName())
                .price(Double.valueOf(createProductRequest.getPrice()))
                .build();
    }


    public Product toProduct() {
        return Product.builder()
                .id(this.id)
                .name(this.name)
                .price(this.price.longValue())
                .build();
    }
}

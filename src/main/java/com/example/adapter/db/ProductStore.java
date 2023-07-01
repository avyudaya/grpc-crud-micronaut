package com.example.adapter.db;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface ProductStore extends CrudRepository<ProductEntity, String> {
}

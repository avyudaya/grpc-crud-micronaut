package com.example.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "with")
@Getter
public class CreateProductRequest {
    private String name;
    private long price;
    private String desc;
}

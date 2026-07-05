package com.diko.productmiddleware.product.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DummyJsonProductDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String thumbnail;
    private String category;
}

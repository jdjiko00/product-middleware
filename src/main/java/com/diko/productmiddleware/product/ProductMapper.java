package com.diko.productmiddleware.product;

import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toDomain(DummyJsonProductDto dto) {

        if (dto == null) {

            return null;
        }

        return Product.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .imageUrl(dto.getThumbnail())
                .category(dto.getCategory())
                .build();
    }
}

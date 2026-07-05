package com.diko.productmiddleware.product.mapper;

import com.diko.productmiddleware.product.Product;
import com.diko.productmiddleware.product.dto.DummyJsonProductDto;
import com.diko.productmiddleware.product.entity.ProductEntity;
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

    public Product toDomain(ProductEntity entity) {

        if (entity == null) {

            return null;
        }

        return Product.builder()
                .id(entity.getId())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .imageUrl(entity.getThumbnail())
                .category(entity.getCategory())
                .build();
    }
}

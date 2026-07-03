package com.diko.productmiddleware.product;

import com.diko.productmiddleware.product.repository.ProductRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Primary
public class DatabaseProductProvider implements ProductProvider {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public DatabaseProductProvider(ProductRepository productRepository, ProductMapper productMapper) {

        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> fetchProducts() {

        return productRepository.findAll().stream()
                .map(productMapper::toDomain)
                .toList();
    }
}

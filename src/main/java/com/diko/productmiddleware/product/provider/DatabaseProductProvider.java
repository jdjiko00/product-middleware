package com.diko.productmiddleware.product.provider;

import com.diko.productmiddleware.product.Product;
import com.diko.productmiddleware.product.mapper.ProductMapper;
import com.diko.productmiddleware.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConditionalOnProperty(name = "product.source", havingValue = "database", matchIfMissing = true)
@RequiredArgsConstructor
public class DatabaseProductProvider implements ProductProvider {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public List<Product> fetchProducts() {

        return productRepository.findAll().stream()
                .map(productMapper::toDomain)
                .toList();
    }
}

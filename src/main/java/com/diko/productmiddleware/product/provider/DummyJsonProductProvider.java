package com.diko.productmiddleware.product.provider;

import com.diko.productmiddleware.exception.ExternalServiceException;
import com.diko.productmiddleware.product.dto.DummyJsonResponse;
import com.diko.productmiddleware.product.Product;
import com.diko.productmiddleware.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
@ConditionalOnProperty(name = "product.source", havingValue = "dummyjson")
@RequiredArgsConstructor
public class DummyJsonProductProvider implements ProductProvider {

    private final RestTemplate restTemplate;
    private final ProductMapper productMapper;

    @Value("${dummyjson.api.url}")
    private String apiUrl;

    @Override
    public List<Product> fetchProducts() {

        DummyJsonResponse response = restTemplate.getForObject(apiUrl, DummyJsonResponse.class);

        if (response == null || response.getProducts() == null) {
            throw new ExternalServiceException("API returned empty response");
        }

        return response.getProducts().stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }
}

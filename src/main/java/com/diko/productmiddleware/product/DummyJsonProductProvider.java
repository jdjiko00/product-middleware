package com.diko.productmiddleware.product;

import com.diko.productmiddleware.exception.ExternalServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DummyJsonProductProvider implements ProductProvider {

    private final RestTemplate restTemplate;
    private final ProductMapper productMapper;
    private final String API_URL = "https://dummyjson.com/products";

    public DummyJsonProductProvider(RestTemplate restTemplate, ProductMapper productMapper) {

        this.restTemplate = restTemplate;
        this.productMapper = productMapper;
    }

    @Override
    public List<Product> fetchProducts() {

        DummyJsonResponse response = restTemplate.getForObject(API_URL, DummyJsonResponse.class);

        if (response == null || response.getProducts() == null) {
            throw new ExternalServiceException("API returned empty response");
        }

        return response.getProducts().stream()
                .map(productMapper::toDomain)
                .collect(Collectors.toList());
    }
}

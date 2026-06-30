package com.diko.productmiddleware.product;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DummyJsonProductProvider implements ProductProvider {

    private final RestTemplate restTemplate;
    private final String API_URL = "https://dummyjson.com/products";

    public DummyJsonProductProvider (RestTemplate restTemplate) {

        this.restTemplate = restTemplate;
    }

    @Override
    public List<Product> fetchProducts() {

        DummyJsonResponse response = restTemplate.getForObject(API_URL, DummyJsonResponse.class);

        if (response == null || response.getProducts() == null) {
            return Collections.emptyList();
        }

        return response.getProducts().stream()
                .map(this::mapToDomain)
                .collect(Collectors.toList());
    }

    private Product mapToDomain(DummyJsonProductDto dto) {

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

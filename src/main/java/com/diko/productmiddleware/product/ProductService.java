package com.diko.productmiddleware.product;

import com.diko.productmiddleware.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductService {

    private final ProductProvider productProvider;

    public ProductService(ProductProvider productProvider) {

        this.productProvider = productProvider;
    }

    @Cacheable(value = "productsCache")
    public List<Product> getProducts(String category, Double minPrice, Double maxPrice, String name) {

        log.info("Fetching products with filters - Category: {}, MinPrice: {}, MaxPrice: {}, Name: {}",
                category, minPrice, maxPrice, name);

        return productProvider.fetchProducts().stream()
                .filter(p -> category == null || p.getCategory().equalsIgnoreCase(category))
                .filter(p -> minPrice == null || p.getPrice() >= minPrice)
                .filter(p -> maxPrice == null || p.getPrice() <= maxPrice)
                .filter(p -> name == null || p.getTitle().toLowerCase().contains(name.toLowerCase()))
                .map(this::abridgedDescription)
                .collect(Collectors.toList());
    }

    @Cacheable(value = "singleProductCache", key = "#id")
    public Product getProductById(Long id) {

        log.info("Fetching product with ID: {}", id);

        return productProvider.fetchProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(this::abridgedDescription)
                .orElseThrow(() -> new ProductNotFoundException("Product with id:" + id + ", not found"));
    }

    private Product abridgedDescription(Product product) {

        if (product.getDescription() != null && product.getDescription().length() > 100) {

            String shortened = product.getDescription().substring(0, 100) + "...";
            product.setDescription(shortened);
        }

        return product;
    }
}

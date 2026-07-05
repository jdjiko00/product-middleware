package com.diko.productmiddleware.product.controller;

import com.diko.productmiddleware.product.Product;
import com.diko.productmiddleware.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController implements ProductApi {

    private final ProductService productService;

    @Override
    @GetMapping
    public ResponseEntity<List<Product>> getProducts(
            @RequestParam(required = false) String category,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) String name) {

        List<Product> products = productService.getProducts(category, minPrice, maxPrice, name);

        return ResponseEntity.ok(products);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {

        Product product = productService.getProductById(id);

        return ResponseEntity.ok(product);
    }
}

package com.diko.productmiddleware.product.provider;

import com.diko.productmiddleware.product.Product;

import java.util.List;

public interface ProductProvider {

    List<Product> fetchProducts();
}

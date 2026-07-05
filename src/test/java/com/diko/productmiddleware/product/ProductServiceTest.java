package com.diko.productmiddleware.product;

import com.diko.productmiddleware.exception.ProductNotFoundException;
import com.diko.productmiddleware.product.provider.ProductProvider;
import com.diko.productmiddleware.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductProvider productProvider;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldReturnFilteredProducts() {

        Product p1 = new Product(1L, "Essence Mascara", "Mascara Lash Princess", 9.99, "image1.jpg", "beauty");
        Product p2 = new Product(2L, "Red Apple", "Fresh Red Apple", 1.99, "image2.jpg", "groceries");

        when(productProvider.fetchProducts()).thenReturn(List.of(p1, p2));

        List<Product> result = productService.getProducts("beauty", 0.0, 15.0, null);

        assertEquals(1, result.size());
        assertEquals("Essence Mascara", result.getFirst().getTitle());
    }

    @Test
    void shouldReturnSingleProductWhenIdExists() {

        Product p = new Product(1L, "Essence Mascara", "Mascara Lash Princess", 9.99, "image1.jpg", "beauty");

        when(productProvider.fetchProducts()).thenReturn(List.of(p));

        Product result = productService.getProductById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Essence Mascara", result.getTitle());
    }

    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        when(productProvider.fetchProducts()).thenReturn(List.of());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(99L));
    }
}

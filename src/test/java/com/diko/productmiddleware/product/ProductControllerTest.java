package com.diko.productmiddleware.product;

import com.diko.productmiddleware.exception.ProductNotFoundException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@WebMvcTest(ProductController.class)
@WithMockUser
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProductService productService;

    @Test
    void shouldReturnProductsAndStatus200() throws Exception {

        Product p = new Product(1L, "Essence Mascara", "Mascara Lash Princess", 9.99, "image1.jpg", "beauty");

        when(productService.getProducts(null, null, null, null)).thenReturn(List.of(p));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$[0].title").value("Essence Mascara"))
                .andExpect(jsonPath("$[0].price").value(9.99));
    }

    @Test
    void shouldReturnSingleProductAndStatus200() throws Exception {

        Product p = new Product(1L, "Essence Mascara", "Mascara Lash Princess", 9.99, "image1.jpg", "beauty");

        when(productService.getProductById(1L)).thenReturn(p);

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(jsonPath("$.title").value("Essence Mascara"))
                .andExpect(jsonPath("$.price").value(9.99));
    }

    @Test
    void shouldReturn404WhenProductNotFound() throws Exception {

        when(productService.getProductById(99L)).thenThrow(new ProductNotFoundException("Product not found with this ID"));

        mockMvc.perform(get("/api/products/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.title").value("Product Not Found"))
                .andExpect(jsonPath("$.detail").value("Product not found with this ID"));
    }
}

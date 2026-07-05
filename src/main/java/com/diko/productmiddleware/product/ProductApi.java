package com.diko.productmiddleware.product;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Product management", description = "Endpoints for retrieving and filtering products")
public interface ProductApi {

    @Operation(summary = "Get all products", description = "Fetches a list of products with optional filtering by category, price range, and name.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the list of products"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access (Basic Auth required)", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<List<Product>> getProducts(
            @Parameter(description = "Filter by product category") String category,
            @Parameter(description = "Minimum price filter") Double minPrice,
            @Parameter(description = "Maximum price filter") Double maxPrice,
            @Parameter(description = "Search products by name (case-insensitive)") String name
    );

    @Operation(summary = "Get a product by ID", description = "Fetches detailed information about a single product based on its unique ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved the product"),
            @ApiResponse(responseCode = "404", description = "Product with the given ID was not found",
                    content = @Content(schema = @Schema(implementation = ProblemDetail.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized access (Basic Auth required)", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    ResponseEntity<Product> getProductById(
            @Parameter(description = "The unique ID of the product") Long id
    );
}

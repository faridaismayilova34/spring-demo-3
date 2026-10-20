package org.example.springdemo3.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import lombok.RequiredArgsConstructor;
import org.example.springdemo3.dto.ProductDto;
import org.example.springdemo3.service.ProductService;
import org.example.springdemo3.validation.Create;
import org.example.springdemo3.validation.Update;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@Validated
@Tag(name = "Product Controller", description = "CRUD and reservation operations for managing products")
public class ProductController {

    private final ProductService productService;

    @Operation(summary = "Get all products", description = "Retrieves a list of all products from the database")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful operation, product list returned",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            )
    })
    @GetMapping
    public List<ProductDto> getAll() {
        return productService.getAll();
    }


    @Operation(summary = "Get product by ID", description = "Retrieves product details by the given ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Product found",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Product not found"
            )
    })
    @GetMapping("/{id}")
    public ProductDto getById(@Parameter(description = "Unique product ID",
            example = "101", required = true)
                              @PathVariable Long id) {
        return productService.getById(id);
    }

    @Operation(summary = "Create a new product", description = "Adds a new product based on the provided DTO data")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Product successfully created",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(responseCode = "400", description = "Validation error")
    })


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@RequestBody @Validated(Create.class) ProductDto productDto) {
        return productService.create(productDto);
    }

    @Operation(summary = "Update a product", description = "Updates existing product details by ID")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successfully updated",
                    content = @Content(schema = @Schema(implementation = ProductDto.class))
            ),
            @ApiResponse(responseCode = "400", description = "Validation error"),
            @ApiResponse(responseCode = "404", description = "Product to update not found")
    })
    @PutMapping("/{id}")
    public ProductDto update(@Parameter(description = "Unique product ID to update", example = "101", required = true)
                             @PathVariable Long id,
                             @RequestBody @Validated(Update.class) ProductDto productDto) {
        return productService.update(id, productDto);
    }


    @Operation(summary = "Delete a product", description = "Deletes a product from the database by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Product successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Product to delete not found")
    })
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@Parameter(description = "Unique product ID to delete", example = "101", required = true)
                       @PathVariable Long id) {
        productService.delete(id);
    }


    @Operation(summary = "Reserve a product", description = "Reserves a product by subtracting the specified quantity from stock")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully reserved"),
            @ApiResponse(responseCode = "400", description = "Invalid quantity or parameter"),
            @ApiResponse(responseCode = "404", description = "Product to reserve not found")
    })
    @PatchMapping("/{id}/reserve")
    public void reserve(@Parameter(description = "Unique product ID to reserve", example = "101", required = true)
                        @PathVariable Long id,
                        @Parameter(description = "Quantity to reserve", example = "5", required = true)
                        @RequestParam @NotNull(message = "Quantity cannot be null")
                        @Positive(message = "Quantity must be greater than 0") Integer quantity) {
        productService.reserve(id, quantity);
    }
}
package org.example.springdemo3.dto;

import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDto {
    private Long id;

    @NotNull(message = "Product name cannot be null")
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @Positive(message = "Price must be greater than 0")
    private BigDecimal price;

    private String type;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Expire date cannot be null")
    @Future(message = "Expire date must be in the future")
    private LocalDate expireDate;

    private Boolean isExpired;
}

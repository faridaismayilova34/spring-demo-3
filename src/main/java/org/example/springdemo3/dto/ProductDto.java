package org.example.springdemo3.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.*;
import org.example.springdemo3.validation.Create;
import org.example.springdemo3.validation.Update;
import org.example.springdemo3.validation.ValidProductDates;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ValidProductDates(groups = {Create.class, Update.class})
@Schema(description = "Product information")
public class ProductDto {

    @Schema(description = "Unique product identifier", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    @NotNull(groups = Update.class, message = "ID cannot be null for update")
    private Long id;

    @Schema(description = "Product name", example = "Smartphone", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "Product name cannot be null", groups = {Create.class, Update.class})
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters",
            groups = {Create.class, Update.class})
    private String name;

    @Schema(description = "Product price", example = "2499.99", minimum = "0")
    @NotNull(message = "Price cannot be null", groups = {Create.class, Update.class})
    @Positive(message = "Price must be greater than 0", groups = {Create.class, Update.class})
    private BigDecimal price;

    @Schema(description = "Product category or type", example = "ELECTRONICS", allowableValues = {"ELECTRONICS", "CLOTHING", "FOOD"})
    private String type;

    @Schema(description = "Product quantity in stock", example = "50", minimum = "1")
    @NotNull(message = "Quantity cannot be null", groups = {Create.class, Update.class})
    @Positive(message = "Quantity must be greater than 0", groups = {Create.class, Update.class})
    private Integer quantity;

    @Schema(description = "Product manufacture date", example = "2026-01-15")
    @NotNull(message = "Manufacture date cannot be null", groups = {Create.class, Update.class})
    @PastOrPresent(message = "Manufacture date cannot be in the future", groups = {Create.class, Update.class})
    private LocalDate manufactureDate;

    @Schema(description = "Product expiration date", example = "2027-01-15")
    @NotNull(message = "Expire date cannot be null", groups = {Create.class, Update.class})
    @Future(message = "Expire date must be in the future", groups = {Create.class, Update.class})
    private LocalDate expiryDate;

    @Schema(description = "Indicates whether the product has expired", example = "false", accessMode = Schema.AccessMode.READ_ONLY)
    private Boolean isExpired;

}

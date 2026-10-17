package org.example.springdemo3.dto;

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
public class ProductDto {

    @NotNull(groups = Update.class, message = "ID cannot be null for update")
    private Long id;

    @NotNull(message = "Product name cannot be null", groups = {Create.class, Update.class})
    @Size(min = 2, max = 50, message = "Product name must be between 2 and 50 characters",
            groups = {Create.class, Update.class})
    private String name;

    @NotNull(message = "Price cannot be null", groups = {Create.class, Update.class})
    @Positive(message = "Price must be greater than 0", groups = {Create.class, Update.class})
    private BigDecimal price;

    private String type;

    @NotNull(message = "Quantity cannot be null", groups = {Create.class, Update.class})
    @Positive(message = "Quantity must be greater than 0", groups = {Create.class, Update.class})
    private Integer quantity;

    @NotNull(message = "Manufacture date cannot be null", groups = {Create.class, Update.class})
    @PastOrPresent(message = "Manufacture date cannot be in the future", groups = {Create.class, Update.class})
    private LocalDate manufactureDate;

    @NotNull(message = "Expire date cannot be null", groups = {Create.class, Update.class})
    @Future(message = "Expire date must be in the future", groups = {Create.class, Update.class})
    private LocalDate expiryDate;

    private Boolean isExpired;

}

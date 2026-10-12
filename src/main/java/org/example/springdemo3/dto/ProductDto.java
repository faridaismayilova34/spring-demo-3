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
    private String name;
    private BigDecimal price;
    private String type;
    private Integer quantity;
    private LocalDate expireDate;
}

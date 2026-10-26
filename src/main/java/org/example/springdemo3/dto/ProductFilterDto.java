package org.example.springdemo3.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class ProductFilterDto {
    private String name;
    private String type;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Boolean isExpired;
    private Long customerId;
    private LocalDate manufactureDateFrom;
    private LocalDate manufactureDateTo;
}
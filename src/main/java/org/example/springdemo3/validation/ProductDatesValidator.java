package org.example.springdemo3.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.springdemo3.dto.ProductDto;

import java.time.LocalDate;

public class ProductDatesValidator implements ConstraintValidator<ValidProductDates, ProductDto> {
    @Override
    public boolean isValid(ProductDto dto, ConstraintValidatorContext context) {
        if (dto == null) {
            return true;
        }

        LocalDate manufactureDate = dto.getManufactureDate();
        LocalDate expiryDate = dto.getExpiryDate();
        LocalDate today = LocalDate.now();

        boolean isValid = true;
        context.disableDefaultConstraintViolation();

        if (manufactureDate == null) {
            addViolation(context, "Manufacture date cannot be null", "manufactureDate");
            isValid = false;
        } else if (manufactureDate.isAfter(today)) {
            addViolation(context, "Manufacture date cannot be in the future", "manufactureDate");
            isValid = false;
        }

        if (expiryDate == null) {
            addViolation(context, "Expiry date cannot be null", "expiryDate");
            isValid = false;
        } else if (expiryDate.isBefore(today)) {
            addViolation(context, "Expiry date cannot be in the past", "expiryDate");
            isValid = false;
        }

        if (manufactureDate != null && expiryDate != null) {
            if (!expiryDate.isAfter(manufactureDate)) {
                addViolation(context, "Expiry date must be after manufacture date", "expiryDate");
                isValid = false;
            }
        }

        return isValid;
    }

    private void addViolation(ConstraintValidatorContext context, String message, String propertyName) {
        context.buildConstraintViolationWithTemplate(message)
                .addPropertyNode(propertyName)
                .addConstraintViolation();
    }
}

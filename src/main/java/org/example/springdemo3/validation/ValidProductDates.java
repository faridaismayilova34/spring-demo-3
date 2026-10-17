package org.example.springdemo3.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductDatesValidator.class)
@Documented
public @interface ValidProductDates {
    String message() default "Invalid product dates";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

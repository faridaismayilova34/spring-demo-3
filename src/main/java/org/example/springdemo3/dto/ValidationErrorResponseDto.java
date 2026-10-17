package org.example.springdemo3.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ValidationErrorResponseDto {
    private int status;
    private String message;
    private List<FieldErrorDto> errors;
}

package org.example.springdemo3.controller;

import lombok.extern.slf4j.Slf4j;
import org.example.springdemo3.dto.ExceptionDto;
import org.example.springdemo3.exception.NotFoundException;
import org.example.springdemo3.exception.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ExceptionDto handleException(Exception ex) {
        log.error("Unexpected error: {}", ex.getMessage());
        return new ExceptionDto("UNEXPECTED_ERROR");
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionDto handleNotFoundException(NotFoundException e) {
        log.error("Not found exception: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionDto handleValidationException(ValidationException e) {
        log.error("Validation exception: {}", e.getMessage());
        return new ExceptionDto(e.getMessage());
    }
}
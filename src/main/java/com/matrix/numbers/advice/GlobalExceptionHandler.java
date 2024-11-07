package com.matrix.numbers.advice;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@ControllerAdvice(annotations=RestController.class)
public class GlobalExceptionHandler {

    @ExceptionHandler(TypeMismatchException.class)
    public Mono<ResponseEntity<Map<String, Map<String, String>>>> handleValidationExceptions(TypeMismatchException ex) {
        return Mono.just(new ResponseEntity<>(Map.of("errors", Map.of((String) Objects.requireNonNull(ex.getValue()), "Invalid value" + ex.getValue())), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Map<String, String>>>> handleMethodArgumentNotValidException(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = error instanceof org.springframework.validation.FieldError ?
                    ((org.springframework.validation.FieldError) error).getField() :
                    error.getObjectName();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        Map<String, Map<String, String>> response = new HashMap<>();
        response.put("errors", errors);

        return Mono.just(new ResponseEntity<>(response, HttpStatus.BAD_REQUEST));
    }
}

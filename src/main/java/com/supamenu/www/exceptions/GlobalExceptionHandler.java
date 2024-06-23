package com.supamenu.www.exceptions;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.utils.ExceptionUtils;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@EnableWebMvc
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ApiResponse<Object>> customException(CustomException ex) {
        return ExceptionUtils.handleResponseException(ex.getException());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ApiResponse<Object>> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, Locale locale) throws JsonProcessingException {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return ApiResponse.error("Unprocessable Entity", HttpStatus.UNPROCESSABLE_ENTITY, errors);
    }
}

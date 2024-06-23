package com.java_ne.utils;

import com.java_ne.exceptions.BadRequestException;
import com.java_ne.exceptions.ConflictException;
import com.java_ne.exceptions.InternalServerErrorException;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.exceptions.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;

public class ExceptionUtils {
    public static ResponseEntity<ApiResponse<Object>> handleResponseException(Exception e) {
        if (e instanceof NotFoundException) {
            return ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.NOT_FOUND,
                    null
            );
        }
        if (e instanceof ConflictException) {
            return ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.CONFLICT,
                    null
            );
        } else if (e instanceof InternalServerErrorException) {
            return ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        } else if (e instanceof BadRequestException || e instanceof InternalAuthenticationServiceException || e instanceof BadCredentialsException || e instanceof AccessDeniedException) {
            return ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    null
            );
        } else {
            return ApiResponse.error(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
}


package com.supamenu.www.exceptions;

import lombok.Getter;

import java.io.Serial;

@Getter
public class BadRequestException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
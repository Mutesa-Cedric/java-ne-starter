package com.supamenu.www.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serial;

@Getter
@AllArgsConstructor
public class ConflictException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    public ConflictException(String message) {
        super(message);
    }

    public ConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}
package com.example.api_package.commons.exceptions;

import org.springframework.http.HttpStatus;

public class ParcelException extends RuntimeException {
    private HttpStatus httpStatus;

    public ParcelException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

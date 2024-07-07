package com.example.api_letters.commons.exceptions;

import org.springframework.http.HttpStatus;

public class LetterException extends RuntimeException {
    private HttpStatus httpStatus;

    public LetterException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

package com.tiep.demoapus.exception;

import org.springframework.http.HttpStatus;

public class EntityNotFoundException extends CustomException {
    public EntityNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}

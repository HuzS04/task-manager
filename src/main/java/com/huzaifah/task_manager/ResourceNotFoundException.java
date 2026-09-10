package com.huzaifah.task_manager;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Custom exception for when a requested resource doesn't exist
// extends RuntimeException = unchecked, no need to declare 'throws' everywhere
// it automatically bubbles up through Service and Controller to GlobalExceptionHandler
@ResponseStatus(HttpStatus.NOT_FOUND)  // safety net — if this escapes uncaught, Spring sends 404
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);  // passes the message up to RuntimeException so getMessage() works
    }
}
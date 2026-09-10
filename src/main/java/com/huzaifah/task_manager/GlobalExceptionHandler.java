package com.huzaifah.task_manager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

// @RestControllerAdvice — this class sits above ALL Controllers
// any exception thrown anywhere in the application gets caught here
// YOU control exactly what every error response looks like
@RestControllerAdvice
public class GlobalExceptionHandler {

    // specifically handles ResourceNotFoundException
    // when that exception is thrown anywhere, Spring runs this method instead of crashing
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(ResourceNotFoundException ex) {

        // Map<String, Object> builds a flexible JSON object
        // each put() call adds one key-value pair to the JSON response
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());  // when did this happen
        error.put("status", 404);                     // HTTP status code
        error.put("error", "Not Found");              // short description
        error.put("message", ex.getMessage());        // your specific message e.g. "Task not found with id: 999"

        // ResponseEntity lets you control BOTH the HTTP status code AND the response body
        // status(HttpStatus.NOT_FOUND) = sends 404
        // body(error) = sends your Map as JSON
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    // catches any other exception that wasn't specifically handled above
    // safety net for unexpected errors — always returns 500 Internal Server Error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneral(Exception ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now());
        error.put("status", 500);
        error.put("error", "Internal Server Error");
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
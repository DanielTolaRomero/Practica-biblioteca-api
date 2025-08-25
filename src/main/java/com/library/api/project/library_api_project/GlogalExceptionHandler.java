package com.library.api.project.library_api_project;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlogalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationException(MethodArgumentNotValidException ex, HttpServletRequest request){
        Map<String, Object> body = new HashMap<>();
        Map<String, Object> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> { 
            errors.put(error.getField(),error.getDefaultMessage());
        });

        body.put("errors", errors);
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("timestamp", LocalDateTime.now());
        body.put("path", request.getMethod());

        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)

    public ResponseEntity handlerDataIntegrityViolationException(DataIntegrityViolationException ex, HttpServletRequest request){
        Map<String,Object> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("tiemstamp", LocalDateTime.now());
        response.put("path", request.getMethod());
        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handlerExceptionIllegalArgumentException(IllegalArgumentException ex, HttpServletRequest request){
        Map<String,Object> response = new HashMap<>();
        response.put("error", ex.getMessage());
        response.put("status", HttpStatus.BAD_REQUEST);
        response.put("tiemstamp", LocalDateTime.now());
        response.put("path", request.getMethod());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);

    }

}

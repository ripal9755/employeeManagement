package com.Ripal.EmployeeManagement.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({AlreadyExistException.class, NotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleAlreadyExistException(RuntimeException e){
        Map<String, Object> response = new HashMap<>();
        response.put("ststus", HttpStatus.BAD_REQUEST);
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}

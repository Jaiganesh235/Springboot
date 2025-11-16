package com.saveetha.exception;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex){
    String message=ex.getMessage();
    int statuscode=404;
    return ResponseEntity.badRequest().body(new ErrorResponse(message,statuscode));
}
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> invalidInputs(MethodArgumentNotValidException ex){
        String message = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(new ErrorResponse(message,1000));
    }

//    @ExceptionHandler(ConstraintViolationException.class)
//    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException ex) {
//        // Extract all validation messages
//        String messages = ex.getConstraintViolations()
//                .stream()
//                .map(ConstraintViolation::getMessage)
//                .collect(Collectors.joining(", "));
//
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(messages);
//    }

}

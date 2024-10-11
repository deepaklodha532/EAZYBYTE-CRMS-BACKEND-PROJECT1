package com.crms.CustomerRelationalManagement.exceptions;

import com.crms.CustomerRelationalManagement.helper.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ApiResponse apiResponse =ApiResponse.builder().message(ex.getMessage()).success(false).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse> handleValidationError(MethodArgumentNotValidException ex) {
        ApiResponse apiResponse =ApiResponse.builder().message(ex.getMessage()).success(false).build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }


}

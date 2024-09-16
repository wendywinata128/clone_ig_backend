package com.commsult_test.clone_ig.controllers;

import java.util.stream.Collectors;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.commsult_test.clone_ig.dto.ErrorResponse;
import com.commsult_test.clone_ig.exceptions.ResponseException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ErrorController {
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse<Object>> globalExceptionHandler(Exception e) {
        log.error("global error exception handler", e);
        ErrorResponse<Object> errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Internal Server Error")
                .error(e.getMessage())
                .build();

        ResponseEntity<ErrorResponse<Object>> response = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        return response;
    }

    @ExceptionHandler(ResponseException.class)
    public ResponseEntity<ErrorResponse<Object>> responseExceptionHandler(ResponseException e) {
        log.error("response error exception handler", e);
        ErrorResponse<Object> errorResponse = ErrorResponse
                .builder()
                .status(e.getStatus())
                .message(e.getMessage())
                .error(e.getError())
                .build();

        var response = new ResponseEntity<>(errorResponse, HttpStatus.valueOf(e.getStatus()));

        return response;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse<Object>> validationExceptionHandler(MethodArgumentNotValidException e) {
        log.error("validation error exception handler", e);
        ErrorResponse<Object> errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Data validation failed")
                .error(e.getFieldErrors().stream().collect(Collectors.toMap(error -> error.getField(), error -> error.getDefaultMessage())))
                .build();

        var response = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        return response;
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse<Object>> authExceptionHandler(AuthenticationException e) {
        log.error("auth error exception handler", e);
        ErrorResponse<Object> errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Authentication Failed")
                .error(e.getMessage())
                .build();

        ResponseEntity<ErrorResponse<Object>> response = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        return response;
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorResponse<Object>> credentialsExceptionHandler(BadCredentialsException e) {
        log.error("credentials error exception handler", e);
        ErrorResponse<Object> errorResponse = ErrorResponse
                .builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message("Authentication Failed")
                .error(e.getMessage())
                .build();

        ResponseEntity<ErrorResponse<Object>> response = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        return response;
    }
}

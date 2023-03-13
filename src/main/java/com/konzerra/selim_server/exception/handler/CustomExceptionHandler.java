package com.konzerra.selim_server.exception.handler;

import com.konzerra.selim_server.exception.NotFoundException;
import com.konzerra.selim_server.exception.response.ErrorResponse;
import com.konzerra.selim_server.exception.response.ValidationErrorResponse;
import com.konzerra.selim_server.exception.FileStorageException;
import com.konzerra.selim_server.exception.PasswordNotConfirmedException;
import com.konzerra.selim_server.exception.UsernameNotUniqueException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler {
    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(EntityNotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handle(NotFoundException e) {
        return ResponseEntity
                .status(NOT_FOUND)
                .body(new ErrorResponse(e.getLocalMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(BAD_REQUEST).body(
                        new ValidationErrorResponse("Validation error", errors));
    }

    @ExceptionHandler(UsernameNotUniqueException.class)
    public ResponseEntity<ErrorResponse> handle(UsernameNotUniqueException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(PasswordNotConfirmedException.class)
    public ResponseEntity<ErrorResponse> handle(PasswordNotConfirmedException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ErrorResponse> handle(FileStorageException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }
}

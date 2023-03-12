package com.konzerra.selim_server.common.exception.handler;

import com.konzerra.selim_server.common.StatusResponse;
import com.konzerra.selim_server.common.ValidationErrorResponse;
import com.konzerra.selim_server.common.exception.FileStorageException;
import com.konzerra.selim_server.common.exception.NewsNotFoundException;
import com.konzerra.selim_server.common.exception.PasswordNotConfirmedException;
import com.konzerra.selim_server.common.exception.UsernameNotUniqueException;
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

    @ExceptionHandler(NewsNotFoundException.class)
    public ResponseEntity<StatusResponse> handle() {
        return ResponseEntity
                .status(NOT_FOUND).body(new StatusResponse(NOT_FOUND));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<StatusResponse> handle(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getAllErrors().forEach((error) -> {

            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity
                .status(BAD_REQUEST).body(new ValidationErrorResponse(BAD_REQUEST, errors));
    }

    @ExceptionHandler(UsernameNotUniqueException.class)
    public ResponseEntity<StatusResponse> handle(UsernameNotUniqueException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new StatusResponse(e.getMessage(), BAD_REQUEST.value()));
    }

    @ExceptionHandler(PasswordNotConfirmedException.class)
    public ResponseEntity<StatusResponse> handle(PasswordNotConfirmedException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(new StatusResponse(e.getMessage(), BAD_REQUEST.value()));
    }
}

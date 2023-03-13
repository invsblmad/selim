package com.konzerra.selim_server.domain.exception;

import lombok.Getter;

@Getter
public class ExceptionResponse {
    private final String message;

    public ExceptionResponse(String message) {
        this.message = message;
    }
}

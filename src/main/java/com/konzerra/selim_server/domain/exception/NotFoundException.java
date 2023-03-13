package com.konzerra.selim_server.domain.exception;

import lombok.Getter;

@Getter
public class NotFoundException extends RuntimeException {
    private final String localMessage;
    public NotFoundException(String message) {
        this.localMessage = message;
    }
}
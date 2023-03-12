package com.konzerra.selim_server.exception;

public class PasswordNotConfirmedException extends RuntimeException {
    public PasswordNotConfirmedException(String message) {
        super(message);
    }
}

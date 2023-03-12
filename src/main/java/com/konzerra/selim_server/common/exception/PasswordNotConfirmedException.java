package com.konzerra.selim_server.common.exception;

public class PasswordNotConfirmedException extends RuntimeException {
    public PasswordNotConfirmedException(String message) {
        super(message);
    }
}

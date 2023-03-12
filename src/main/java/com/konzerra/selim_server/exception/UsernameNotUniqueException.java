package com.konzerra.selim_server.exception;

public class UsernameNotUniqueException extends RuntimeException {
    public UsernameNotUniqueException(String message) {
        super(message);
    }
}

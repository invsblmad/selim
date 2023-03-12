package com.konzerra.selim_server.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
@Setter
public class ValidationErrorResponse extends StatusResponse {

    private Map<String, String> errors;

    public ValidationErrorResponse(HttpStatus httpStatus, Map<String, String> errors) {
        super(httpStatus);
        this.errors = errors;
    }
}

package com.konzerra.selim_server.domain.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class StatusResponse {
    private String message;
    private int status;

    public StatusResponse(HttpStatus httpStatus) {
        this.message = httpStatus.getReasonPhrase();
        this.status = httpStatus.value();
    }

    public StatusResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }
}

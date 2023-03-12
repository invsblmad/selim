package com.konzerra.selim_server.domain.auth.dto;

import lombok.Data;

@Data
public class AuthenticationDto {
    private String username;
    private String password;
}

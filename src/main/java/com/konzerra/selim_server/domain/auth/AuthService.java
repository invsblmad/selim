package com.konzerra.selim_server.domain.auth;

import com.konzerra.selim_server.domain.auth.dto.AuthenticationDto;
import com.konzerra.selim_server.domain.auth.dto.RegistrationDto;
import com.konzerra.selim_server.domain.security.jwt.TokenResponse;

public interface AuthService {
    TokenResponse login(AuthenticationDto authenticationDto);
    TokenResponse register(RegistrationDto registrationDto);
}

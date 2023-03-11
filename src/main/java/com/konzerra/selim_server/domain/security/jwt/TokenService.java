package com.konzerra.selim_server.domain.security.jwt;


import com.konzerra.selim_server.domain.security.UserDetailsImpl;

public interface TokenService {
    TokenResponse generateToken(UserDetailsImpl userDetails);
}

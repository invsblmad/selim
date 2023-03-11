package com.konzerra.selim_server.domain.jwt;


import com.konzerra.selim_server.domain.jwt.security.UserDetailsImpl;

public interface TokenService {
    TokenResponse generateToken(UserDetailsImpl userDetails);
}

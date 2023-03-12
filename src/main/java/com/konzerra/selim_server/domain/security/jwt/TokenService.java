package com.konzerra.selim_server.domain.security.jwt;


import com.konzerra.selim_server.domain.security.UserDetailsImpl;
import com.konzerra.selim_server.domain.user.User;

public interface TokenService {
    TokenResponse generateToken(UserDetailsImpl userDetails);
    User getUserFromToken();
}

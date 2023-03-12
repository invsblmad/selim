package com.konzerra.selim_server.domain.auth;

import com.konzerra.selim_server.domain.auth.dto.AuthenticationDto;
import com.konzerra.selim_server.domain.auth.dto.RegistrationDto;
import com.konzerra.selim_server.domain.security.jwt.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public TokenResponse login(@RequestBody AuthenticationDto authenticationDto) throws AuthenticationException {
        return authService.login(authenticationDto);
    }

    @PostMapping("/register")
    public TokenResponse register(@Valid @RequestBody RegistrationDto registrationDto) {
        return authService.register(registrationDto);
    }
}

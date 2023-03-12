package com.konzerra.selim_server.domain.security.jwt.impl;

import com.konzerra.selim_server.domain.security.UserDetailsServiceImpl;
import com.konzerra.selim_server.domain.security.jwt.TokenResponse;
import com.konzerra.selim_server.domain.security.jwt.TokenService;
import com.konzerra.selim_server.domain.security.UserDetailsImpl;
import com.konzerra.selim_server.domain.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {
    private final JwtEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;
    @Override
    public TokenResponse generateToken(UserDetailsImpl userDetails) {
        JwtClaimsSet claims = getJwtClaims(userDetails);
        String jwtToken = encoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
        return new TokenResponse(jwtToken);
    }

    private JwtClaimsSet getJwtClaims(UserDetailsImpl userDetails) {
        Instant now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(userDetails.getUsername())
                .id(String.valueOf(userDetails.user().getId()))
                .claim("scope", getScope(userDetails))
                .build();
    }

    private String getScope(UserDetailsImpl userDetails) {
        return userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    @Override
    public User getUserFromToken() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Jwt jwt = (Jwt) authentication.getPrincipal();
        UserDetailsImpl userDetails = userDetailsService.loadUserByUsername(jwt.getSubject());
        return userDetails.user();
    }


}

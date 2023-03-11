package com.konzerra.selim_server.domain.auth.impl;

import com.konzerra.selim_server.domain.auth.AuthService;
import com.konzerra.selim_server.domain.auth.dto.AuthenticationDto;
import com.konzerra.selim_server.domain.auth.dto.RegistrationDto;
import com.konzerra.selim_server.domain.common.exception.PasswordNotConfirmedException;
import com.konzerra.selim_server.domain.common.exception.UsernameNotUniqueException;
import com.konzerra.selim_server.domain.security.jwt.TokenResponse;
import com.konzerra.selim_server.domain.security.jwt.TokenService;
import com.konzerra.selim_server.domain.security.UserDetailsImpl;
import com.konzerra.selim_server.domain.user.User;
import com.konzerra.selim_server.domain.user.UserMapper;
import com.konzerra.selim_server.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository usersRepository;

    private final UserMapper userMapper;

    @Override
    public TokenResponse login(AuthenticationDto authenticationDto) {
        Authentication authObject = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationDto.getUsername(),
                        authenticationDto.getPassword()
                ));
        SecurityContextHolder.getContext().setAuthentication(authObject);
        return tokenService.generateToken((UserDetailsImpl) authObject.getPrincipal());
    }

    @Override
    public TokenResponse register(RegistrationDto registrationDto) {
        checkUsernameForUniqueness(registrationDto.getUsername());
        checkPasswordConfirmation(registrationDto.getPassword(), registrationDto.getPasswordConfirmation());

        User user = saveUser(registrationDto);
        return tokenService.generateToken(new UserDetailsImpl(user));
    }

    private void checkUsernameForUniqueness(String username) {
        if (usersRepository.findByUsername(username).isPresent())
            throw new UsernameNotUniqueException("Such username is taken");
    }

    private void checkPasswordConfirmation(String password, String confirmation) {
        if (!password.equals(confirmation))
            throw new PasswordNotConfirmedException("The password hasn't been confirmed");
    }

    private User saveUser(RegistrationDto registrationDto) {
        User user = userMapper.reegistrationDtoToUser(registrationDto);
        encodePassword(user);
        return usersRepository.save(user);
    }

    private void encodePassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}

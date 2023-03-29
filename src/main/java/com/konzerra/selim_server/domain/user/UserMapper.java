package com.konzerra.selim_server.domain.user;

import com.konzerra.selim_server.domain.auth.dto.RegistrationDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User dtoToUser(RegistrationDto registrationDto);
}

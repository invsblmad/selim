package com.konzerra.selim_server.domain.auth.dto;

import com.konzerra.selim_server.domain.user.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationDto {
        @NotBlank(message = "The last name can't be null or empty")
        private String lastName;

        @NotBlank(message = "The first name can't be null or empty")
        private String firstName;

        @NotBlank(message = "The username can't be null or empty")
        private String username;

        @NotBlank(message = "The password can't be null or empty")
        @Size(min = 5, message = "The password must be at least 5 characters long")
        private String password;

        private String passwordConfirmation;

        private Role role;
}

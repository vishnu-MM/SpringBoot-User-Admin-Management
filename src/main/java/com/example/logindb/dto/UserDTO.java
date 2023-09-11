package com.example.logindb.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private int id;

    @NotEmpty(message = "Name can not be null ")
    private String name;

    @Email
    @NotEmpty(message = "Email can not be null ")
    private String username;

    private String role;

    @NotEmpty(message = "Password can not be null ")
    private String password;

    // Constructors, getters, and setters
}

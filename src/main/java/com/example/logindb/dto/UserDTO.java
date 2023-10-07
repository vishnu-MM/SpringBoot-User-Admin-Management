package com.example.logindb.dto;

import jakarta.validation.constraints.*;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {

    private int id;

    @NotEmpty(message = "Name must be filled")
    private String name;

    @Email(message = "Invalid email address")
    @NotEmpty(message = "Email should be filled")
    private String username;

    private String role;

    @NotEmpty
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
}

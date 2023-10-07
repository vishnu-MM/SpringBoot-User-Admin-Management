package com.example.logindb.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Validated
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String username;

    @Column(name = "role")
    private String role;

    @NotBlank(message = "Password can not be null ")
    @NotNull(message = "Password can not be null ")
    @NotEmpty(message = "Password can not be null ")
    @Column(name = "password")
    private String password;

    public User(String name, String email, String role) {
        this.name = name;
        this.username = email;
        this.role = role;
    }
}

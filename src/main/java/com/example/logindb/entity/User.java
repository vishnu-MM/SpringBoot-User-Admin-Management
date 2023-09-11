package com.example.logindb.entity;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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

    @Column(name = "password")
    private String password;

    public User(String name, String email, String role) {
        this.name = name;
        this.username = email;
        this.role = role;
    }
}

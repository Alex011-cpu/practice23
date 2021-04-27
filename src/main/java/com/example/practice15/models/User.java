package com.example.practice15.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_name")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "session_id")
    private String sessionId;
    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;
}

package com.hacheery.todoapp.entity;

import com.hacheery.todoapp.enums.ERole;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @ElementCollection(targetClass = ERole.class)
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles = new HashSet<>();
}

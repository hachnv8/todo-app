package com.hacheery.todoapp.dto;

import com.hacheery.todoapp.enums.ERole;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String username;
    private String email;

    @ElementCollection(targetClass = ERole.class)
    @Enumerated(EnumType.STRING)
    private Set<ERole> roles = new HashSet<>();
}

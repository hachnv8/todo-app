package com.hacheery.todoapp.service;

import com.hacheery.todoapp.entity.User;

public interface JwtService {
    String extractUsername(String token);
    String generateToken(User user);
}

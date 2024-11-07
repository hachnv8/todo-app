package com.hacheery.todoapp.service;

import com.hacheery.todoapp.payload.request.AuthenticationRequest;
import com.hacheery.todoapp.payload.request.RegisterRequest;
import com.hacheery.todoapp.payload.response.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(AuthenticationRequest request);
}

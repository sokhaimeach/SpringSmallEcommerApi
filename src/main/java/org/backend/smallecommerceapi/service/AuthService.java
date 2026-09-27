package org.backend.smallecommerceapi.service;

import org.backend.smallecommerceapi.dto.auth.AuthResponse;
import org.backend.smallecommerceapi.dto.auth.LoginRequest;
import org.backend.smallecommerceapi.dto.auth.RegisterRequest;
import org.backend.smallecommerceapi.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public AuthResponse register(RegisterRequest request) {}
//    public AuthResponse login(LoginRequest request) {}
}

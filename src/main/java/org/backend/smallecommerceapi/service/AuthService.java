package org.backend.smallecommerceapi.service;

import org.backend.smallecommerceapi.dto.auth.AuthResponse;
import org.backend.smallecommerceapi.dto.auth.LoginRequest;
import org.backend.smallecommerceapi.dto.auth.RegisterRequest;
import org.backend.smallecommerceapi.entity.User;
import org.backend.smallecommerceapi.exception.ConflictException;
import org.backend.smallecommerceapi.mapper.UserMapper;
import org.backend.smallecommerceapi.repository.UserRepository;
import org.backend.smallecommerceapi.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final UserMapper userMapper;

    public AuthService(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
    }

    public AuthResponse register(RegisterRequest request) {
        // Check whether the email is already registered.
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ConflictException("Email already exists");
        }

        User user = userMapper.fromRegisterToEntity(request);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userRepository.save(user);

        // Authenticate the newly registered user
        Authentication authenticationRequest = UsernamePasswordAuthenticationToken
                .unauthenticated(
                        request.getEmail(),
                        request.getPassword()
                );
        Authentication authentication = authenticationManager.authenticate(authenticationRequest);

        // generate token
        String token = jwtService.generateToken(authentication);

        return userMapper.toAuthResponse(user, token);
    }
    public AuthResponse login(LoginRequest request) {
        // Authenticate email and password
        Authentication authentication = authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken
                        .unauthenticated(
                                request.getEmail(),
                                request.getPassword()
                        )
        );

        // Generate token
        String token = jwtService.generateToken(authentication);

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        return userMapper.toAuthResponse(user, token);
    }
}

package com.example.recommendation.service;

import com.example.recommendation.model.User;
import com.example.recommendation.model.dto.AuthRequest;
import com.example.recommendation.model.dto.AuthResponse;
import com.example.recommendation.model.dto.RegisterRequest;
import com.example.recommendation.repository.UserRepository;
import com.example.recommendation.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Check if email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        // Create new user
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        // Generate token
        String token = jwtUtil.createToken(user.getUsername());

        return new AuthResponse(token, user.getUsername());
    }

    public AuthResponse login(AuthRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        String token = jwtUtil.createToken(authentication.getName());

        return new AuthResponse(token, request.getUsername());
    }

    public boolean validateToken(String token) {
        return jwtUtil.validateToken(token);
    }
}
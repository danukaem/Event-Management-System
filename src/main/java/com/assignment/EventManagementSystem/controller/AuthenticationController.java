package com.assignment.EventManagementSystem.controller;

import com.assignment.EventManagementSystem.entity.Role;
import com.assignment.EventManagementSystem.entity.User;
import com.assignment.EventManagementSystem.dto.AuthenticationRequest;
import com.assignment.EventManagementSystem.repository.UserRepo;
import com.assignment.EventManagementSystem.service.CustomUserDetailsService;
import com.assignment.EventManagementSystem.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
        } catch (Exception e) {
            throw new Exception("Invalid username or password", e);
        }
        final UserDetails user = customUserDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(user);
        Map<String, Object> response = new HashMap<>();
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            response.put("userId", userRepo.findByUsername(request.getUsername()).get().getId());
        }
        response.put("token", jwt);
        response.put("username", user.getUsername());
        response.put("authorities", user.getAuthorities());
        response.put("message", "User authenticated successfully");
        return ResponseEntity.ok(response);
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody AuthenticationRequest request) {
        if (userRepo.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity.badRequest().body("Username already exists.");
        }
        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(request.getRole().toUpperCase()))
                .email(request.getEmail())
                .build();
        userRepo.save(user);
        Map<String, Object> response = new HashMap<>();
        response.put( "username", user.getUsername());
        response.put( "userId", user.getId());
        response.put("authorities", user.getRole());
        response.put("message", "User registered successfully");
        return ResponseEntity.ok(response);
    }
}
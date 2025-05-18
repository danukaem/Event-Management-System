package com.assignment.EventManagementSystem.dto;

import lombok.Data;

@Data
public class AuthenticationRequest {
    private String username;
    private String password;
    private String role;
    private String email;
}

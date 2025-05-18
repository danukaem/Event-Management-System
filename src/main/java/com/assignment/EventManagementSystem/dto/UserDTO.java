package com.assignment.EventManagementSystem.dto;

import com.assignment.EventManagementSystem.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {
    private String name;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;
    private Role role;
    private String password;

}

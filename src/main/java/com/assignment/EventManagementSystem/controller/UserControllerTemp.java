package com.assignment.EventManagementSystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserControllerTemp {

    @GetMapping("hello1")
    public String userHome() {

        return "Hello World from UserController!";
    }

    @GetMapping("hello2")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userHome2() {
        return "Hello World from UserController 2!";
    }

    @GetMapping("hello3")
    @PreAuthorize("hasRole('ADMIN')")
    public String userHome3() {
        return "Hello World from UserController 3!";
    }


}

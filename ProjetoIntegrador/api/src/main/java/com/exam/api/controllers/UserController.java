package com.exam.api.controllers;

import com.exam.api.models.AuthRequest;
import com.exam.api.models.User;
import com.exam.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
        return userService.getAuthToken(authRequest.getUsername(), authRequest.getPassword());
    }

    @PostMapping("/sign-up")
    public String createUserWithAuth(@RequestBody User user) throws Exception {
        userService.save(user);
        return userService.getAuthToken(user.getUsername(), user.getPassword());
    }

    @GetMapping("/user/current")
    public User getUser() {
        return userService.getCurrentUser();
    }
}

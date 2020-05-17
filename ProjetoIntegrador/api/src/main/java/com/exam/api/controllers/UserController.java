package com.exam.api.controllers;

import com.exam.api.models.AuthRequest;
import com.exam.api.models.User;
import com.exam.api.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/user/current")
    public User updateUser(@RequestBody User user) {
        User currentUser = userService.getCurrentUser();
        currentUser.setName(user.getName());
        currentUser.setBirthday(user.getBirthday());
        return userService.save(currentUser);
    }
}

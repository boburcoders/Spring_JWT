package com.company.spring_security.controller;

import com.company.spring_security.model.Users;
import com.company.spring_security.service.JwtService;
import com.company.spring_security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    @PostMapping("register")
    public Users saveUser(@RequestBody Users user) {
        return this.userService.saveUser(user);
    }

    @PostMapping("login")
    public String login(@RequestBody Users user) {
        Authentication authentication = this.authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            return this.jwtService.getJwtToken(user.getUsername());
        }
        return "Failed to login";
    }


    @GetMapping("get-all")
    public List<Users> getAllUsers() {
        return this.userService.getAllUsers();
    }


    @GetMapping("/{id}")
    public Users getUserById(@PathVariable(value = "id") int id) {
        return this.userService.findById(id);
    }

    @DeleteMapping("/{id}")
    public String deleteUserById(@PathVariable(value = "id") int id) {
        this.userService.deleteUserById(id);
        return String.format("User with id '%d' deleted", id);
    }

    @PutMapping
    public Users updateUserById(@RequestBody Users user) {
        return this.userService.saveUser(user);
    }
}

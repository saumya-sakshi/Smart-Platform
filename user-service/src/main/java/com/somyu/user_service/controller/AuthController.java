package com.somyu.user_service.controller;

import com.somyu.user_service.dto.AuthResponse;
import com.somyu.user_service.dto.LoginRequest;
import com.somyu.user_service.entity.User;
import com.somyu.user_service.service.JwtService;
import com.somyu.user_service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

   private final UserService service;
   @Autowired
   AuthenticationManager authenticationManager;

   private final JwtService jwtService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        if(authentication.isAuthenticated())
            return jwtService.generateToken(request.getUsername());
        else
            return "Login Failed";


    }
}


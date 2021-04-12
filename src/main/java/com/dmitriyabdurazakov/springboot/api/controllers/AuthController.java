package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.service.config.security.JwtUserValidator;
import com.dmitriyabdurazakov.springboot.service.config.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private JwtProvider jwtProvider;
    @Autowired
    private JwtUserValidator jwtUserValidator;

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
//        if (jwtUserValidator.validateAdminLoginAndPassword(request.getLogin(), request.getPassword())) {
            String token = jwtProvider.generateToken(request.getLogin());
            return new AuthResponse(token);
//        }
//        return null;
    }
}

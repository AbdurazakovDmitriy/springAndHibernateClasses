package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.api.dto.AuthRequest;
import com.dmitriyabdurazakov.springboot.api.dto.AuthResponse;
import com.dmitriyabdurazakov.springboot.api.dto.RegistrationRequest;
import com.dmitriyabdurazakov.springboot.data.entity.UserEntity;
import com.dmitriyabdurazakov.springboot.service.config.security.jwt.JwtProvider;
import com.dmitriyabdurazakov.springboot.service.management.UserEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class AuthController {
    private final UserEntityService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/register")
    public String registerUser(@RequestBody @Valid RegistrationRequest registrationRequest) {
        UserEntity userEntity = new UserEntity();
        userEntity.setPassword(registrationRequest.getPassword());
        userEntity.setLogin(registrationRequest.getLogin());
        userService.saveUser(userEntity);
        return "OK";
    }

    @PostMapping("/auth")
    public AuthResponse auth(@RequestBody AuthRequest request) {
        UserEntity userEntity = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthResponse(token);
    }
}

package com.dmitriyabdurazakov.springboot.service.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtUserValidator {
    @Value("${admin.password}")
    private String password;
    private final PasswordEncoder passwordEncoder;

    public boolean validateAdminLoginAndPassword(String login, String password) {
        if (login == null || login.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }
        return "admin".equals(login) && passwordEncoder.encode(password).equals(this.password);
    }
}

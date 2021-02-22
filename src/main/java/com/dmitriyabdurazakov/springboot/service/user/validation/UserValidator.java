package com.dmitriyabdurazakov.springboot.service.user.validation;

import com.dmitriyabdurazakov.springboot.data.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserValidator {
    private final Validator validator;

    @SneakyThrows
    public boolean validate(User user) {
        Set<ConstraintViolation<User>> violations = validator.validate(user);
        for (ConstraintViolation<User> violation : violations) {
            log.warn(violation.getMessage());
        }
        return violations.isEmpty();
    }
}

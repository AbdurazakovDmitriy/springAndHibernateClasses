package com.dmitriyabdurazakov.springboot.service;


import com.dmitriyabdurazakov.springboot.entity.User;
import com.dmitriyabdurazakov.springboot.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

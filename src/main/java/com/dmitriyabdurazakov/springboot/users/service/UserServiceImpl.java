package com.dmitriyabdurazakov.springboot.users.service;


import com.dmitriyabdurazakov.springboot.users.entity.User;
import com.dmitriyabdurazakov.springboot.users.repositories.UserRepository;
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

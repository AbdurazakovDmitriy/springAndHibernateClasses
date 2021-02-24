package com.dmitriyabdurazakov.springboot.service.management;


import com.dmitriyabdurazakov.springboot.data.entity.User;
import com.dmitriyabdurazakov.springboot.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}

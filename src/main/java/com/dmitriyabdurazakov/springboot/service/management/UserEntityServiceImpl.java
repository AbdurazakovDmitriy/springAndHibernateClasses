package com.dmitriyabdurazakov.springboot.service.management;

import com.dmitriyabdurazakov.springboot.data.entity.RoleEntity;
import com.dmitriyabdurazakov.springboot.data.entity.UserEntity;
import com.dmitriyabdurazakov.springboot.data.repository.RoleEntityRepository;
import com.dmitriyabdurazakov.springboot.data.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserEntityServiceImpl implements UserEntityService {

    private final UserEntityRepository userEntityRepository;
    private final RoleEntityRepository roleEntityRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserEntity saveUser(UserEntity userEntity) {
        RoleEntity userRole = roleEntityRepository.findByName("ROLE_USER");
        userEntity.setRoleEntity(userRole);
        userEntity.setPassword(passwordEncoder.encode(userEntity.getPassword()));
        return userEntityRepository.save(userEntity);
    }

    @Override
    public UserEntity findByLogin(String login) {
        return userEntityRepository.findByLogin(login);
    }

    @Override
    public UserEntity findByLoginAndPassword(String login, String password) {
        UserEntity userEntity = findByLogin(login);
        if (userEntity != null) {
            if (passwordEncoder.matches(password, userEntity.getPassword())) {
                return userEntity;
            }
        }
        return null;
    }
}

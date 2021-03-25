package com.dmitriyabdurazakov.springboot.service.management;

import com.dmitriyabdurazakov.springboot.data.entity.UserEntity;


public interface UserEntityService {

    UserEntity saveUser(UserEntity userEntity);

    UserEntity findByLogin(String login);

    UserEntity findByLoginAndPassword(String login, String password);
}

package com.dmitriyabdurazakov.springboot.data.repository;

import com.dmitriyabdurazakov.springboot.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByLogin(String login);
}

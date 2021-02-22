package com.dmitriyabdurazakov.springboot.data.repository;

import com.dmitriyabdurazakov.springboot.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

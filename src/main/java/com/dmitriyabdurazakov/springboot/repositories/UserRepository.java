package com.dmitriyabdurazakov.springboot.repositories;

import com.dmitriyabdurazakov.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

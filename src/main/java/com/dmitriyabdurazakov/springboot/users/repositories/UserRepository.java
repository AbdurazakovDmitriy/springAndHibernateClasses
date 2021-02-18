package com.dmitriyabdurazakov.springboot.users.repositories;

import com.dmitriyabdurazakov.springboot.users.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}

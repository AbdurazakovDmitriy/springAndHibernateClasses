package com.dmitriyabdurazakov.springboot.data.repository;

import com.dmitriyabdurazakov.springboot.data.entity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {

    RoleEntity findByName(String name);
}

package com.dmitriyabdurazakov.springboot.repositories;

import com.dmitriyabdurazakov.springboot.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

package com.dmitriyabdurazakov.springboot.data.repository;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

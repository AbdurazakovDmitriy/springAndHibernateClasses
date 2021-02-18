package com.dmitriyabdurazakov.springboot.categories.repositories;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}

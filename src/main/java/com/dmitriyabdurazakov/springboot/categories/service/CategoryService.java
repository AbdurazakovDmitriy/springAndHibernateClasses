package com.dmitriyabdurazakov.springboot.categories.service;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> saveAll(List<Category> categories);

    Category findById(Long id);

    List<Category> findAllById(List<Long> ids);

    List<Category> findAll();
}

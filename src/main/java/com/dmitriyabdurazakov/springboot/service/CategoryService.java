package com.dmitriyabdurazakov.springboot.service;

import com.dmitriyabdurazakov.springboot.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> saveAll(List<Category> categories);

    Category findById(Long id);

    List<Category> findAllById(List<Long> ids);
}

package com.dmitriyabdurazakov.springboot.service.category;

import com.dmitriyabdurazakov.springboot.data.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> saveAll(List<Category> categories);

    Category findById(Long id);

    List<Category> findAllById(List<Long> ids);

    List<Category> findAll();

    List<Category> saveAllCategoriesFromFilePaths(List<String> categoriesFilesPaths);
}

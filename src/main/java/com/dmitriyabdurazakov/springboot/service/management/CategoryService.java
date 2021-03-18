package com.dmitriyabdurazakov.springboot.service.management;

import com.dmitriyabdurazakov.springboot.data.entity.Category;

import java.util.List;

public interface CategoryService {

    List<Category> saveAllCategoriesFromFilePaths(List<String> categoriesFilesPaths);

    Category saveCategory(Category category);

    Category findCategoryById(Long id);

    List<Category> getCategoriesByProductId(Long productId);
}

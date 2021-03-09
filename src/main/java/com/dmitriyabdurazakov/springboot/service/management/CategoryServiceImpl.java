package com.dmitriyabdurazakov.springboot.service.management;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.data.repository.CategoryRepository;
import com.dmitriyabdurazakov.springboot.service.collectors.CategoryCollector;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryCollector categoryCollector;

    @Override
    public List<Category> saveAllCategoriesFromFilePaths(List<String> categoriesFilesPaths) {
        List<Category> categoryList = categoryCollector.getCategories(categoriesFilesPaths);
        return categoryRepository.saveAll(categoryList);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }
}

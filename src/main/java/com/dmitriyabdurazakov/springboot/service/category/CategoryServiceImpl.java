package com.dmitriyabdurazakov.springboot.service.category;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.data.repository.CategoryRepository;
import com.dmitriyabdurazakov.springboot.service.category.parsers.CategoryParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryParser categoryParser;

    @Override
    public List<Category> saveAll(List<Category> categories) {
        return categoryRepository.saveAll(categories);
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public List<Category> findAllById(List<Long> ids) {
        return categoryRepository.findAllById(ids);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<Category> saveAllCategoriesFromFilePaths(List<String> categoriesFilesPaths) {
        List<Category> categoryList = categoryParser.getCategories(categoriesFilesPaths);
        return categoryRepository.saveAll(categoryList);
    }
}

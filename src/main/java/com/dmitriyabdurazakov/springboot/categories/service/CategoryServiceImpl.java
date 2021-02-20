package com.dmitriyabdurazakov.springboot.categories.service;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;
import com.dmitriyabdurazakov.springboot.categories.repositories.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

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
}

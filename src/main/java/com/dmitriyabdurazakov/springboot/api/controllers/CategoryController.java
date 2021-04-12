package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.service.dto.CategoryDTO;
import com.dmitriyabdurazakov.springboot.service.management.CategoryService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Api("Контроллер для работы с категориями")
public class CategoryController {
    private final CategoryService categoryService;
    private final ModelMapper modelMapper = new ModelMapper();

    @PostMapping(path = "/categories", consumes = MediaType.ALL_VALUE)
    public Category addCategory(@Valid CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        return categoryService.saveCategory(category);
    }

    @GetMapping(value = "/categories/{id}")
    public Category getCategoryById(@PathVariable Long id) {
        return categoryService.findCategoryById(id);
    }

    @GetMapping(path = "/products/{id}/categories")
    public List<Category> getCategoriesByProductId(@PathVariable Long id) {
        return categoryService.getCategoriesByProductId(id);
    }
}

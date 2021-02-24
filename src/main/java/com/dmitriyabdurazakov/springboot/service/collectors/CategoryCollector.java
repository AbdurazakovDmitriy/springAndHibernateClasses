package com.dmitriyabdurazakov.springboot.service.collectors;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.service.dto.CategoryDTO;
import com.dmitriyabdurazakov.springboot.service.mappers.CategoryMapper;
import com.dmitriyabdurazakov.springboot.service.parsers.CategoryDtoParser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryCollector {
    private final CategoryMapper categoryMapper;
    private final CategoryDtoParser categoryDtoParser;

    public List<Category> getCategories(List<String> paths) {
        List<CategoryDTO> categoryDTOS = categoryDtoParser.getCategoryDTOList(paths);
        List<Category> categoryList = new ArrayList<>();
        categoryDTOS.forEach(categoryDTO -> {
            Category category = categoryMapper.mapCategoryDtoToCategory(categoryDTO);
            categoryList.add(category);
        });
        return categoryList;
    }
}

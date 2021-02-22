package com.dmitriyabdurazakov.springboot.service.category.parsers;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.service.category.dto.CategoryDTO;
import com.dmitriyabdurazakov.springboot.service.category.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryParser {
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

package com.dmitriyabdurazakov.springboot.parsers;

import com.dmitriyabdurazakov.springboot.entity.Category;
import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.entity.dto.CategoryDTO;
import com.dmitriyabdurazakov.springboot.entity.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.mappers.CategoryMapper;
import com.dmitriyabdurazakov.springboot.mappers.ProductMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CategoryParser {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ProductMapper productMapper;
    private final CategoryMapper categoryMapper;

    public List<CategoryDTO> getCategoryDTOList(List<String> paths) {
        List<CategoryDTO> categoryDTOS = new ArrayList<>();
        paths.forEach(path -> {
            try {
                categoryDTOS.add(objectMapper.readValue(new File(path), CategoryDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return categoryDTOS;
    }

    public List<Category> getCategories(List<String> paths) {
        List<CategoryDTO> categoryDTOS = getCategoryDTOList(paths);
        List<Category> categoryList = new ArrayList<>();
        categoryDTOS.forEach(categoryDTO -> {
            Category category = categoryMapper.mapCategoryDtoToCategory(categoryDTO);
            List<ProductDTO> productsDto = categoryDTO.getProductsDto();
            List<Product> productList = productsDto
                .stream()
                .map(productMapper::mapProductDtoToProduct)
                .collect(Collectors.toList());
            productList.forEach(product -> product.setCategories(Collections.singletonList(category)));
            category.setProducts(productList);
            categoryList.add(category);
        });
        return categoryList;
    }
}

package com.dmitriyabdurazakov.springboot.categories.mappers;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;
import com.dmitriyabdurazakov.springboot.products.entity.Product;
import com.dmitriyabdurazakov.springboot.categories.entity.CategoryDTO;
import com.dmitriyabdurazakov.springboot.products.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private final ProductMapper productMapper;

    public Category mapCategoryDtoToCategory(CategoryDTO categoryDTO) {
        List<Product> productList = new ArrayList<>();
        categoryDTO.getProductsDto().forEach(productDto -> productList.add(productMapper.mapProductDtoToProduct(productDto)));
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setProducts(productList);
        return category;
    }
}

package com.dmitriyabdurazakov.springboot.service.category.mappers;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.api.dto.CategoryDTO;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.product.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryMapper {
    private final ModelMapper modelMapper = new ModelMapper();
    private final ProductMapper productMapper;

    public Category mapCategoryDtoToCategory(CategoryDTO categoryDTO) {
        List<Product> productList = new ArrayList<>();
        categoryDTO.getProductsDto().forEach(productDto -> productList.add(productMapper.mapProductDtoToProduct(productDto)));
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.getProducts().clear();
        productList.forEach(product -> product.addCategory(category));
        return category;
    }
}

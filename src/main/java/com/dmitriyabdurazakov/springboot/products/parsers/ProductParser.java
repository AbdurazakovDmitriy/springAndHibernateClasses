package com.dmitriyabdurazakov.springboot.products.parsers;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;
import com.dmitriyabdurazakov.springboot.categories.service.CategoryService;
import com.dmitriyabdurazakov.springboot.products.entity.Product;
import com.dmitriyabdurazakov.springboot.products.entity.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.products.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductParser {
    private final ProductMapper productMapper;
    private final CategoryService categoryService;
    private final ProductDtoParser productDtoParser;

    public List<Product> getProductList(List<String> paths) {
        List<ProductDTO> productDTOList = productDtoParser.getProductDTOList(paths);
        List<Category> allCategories = categoryService.findAll();

        List<Product> productList = new ArrayList<>();
        productDTOList.forEach(dto -> {
            Product product = productMapper.mapProductDtoToProduct(dto);
            List<Category> categories = new ArrayList<>();
            dto.getCategoriesIds().forEach(id -> {
                allCategories.forEach(category -> {
                    if (category.getId().equals(id)) {
                        categories.add(category);
                    }
                });
            });

            if (categories.size() < dto.getCategoriesIds().size()) {
                throw new RuntimeException("Не найдена одна или несколько категорий с ID " + dto.getCategoriesIds());
            }
            product.addCategories(categories);
            productList.add(product);
        });
        return productList;
    }
}

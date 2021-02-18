package com.dmitriyabdurazakov.springboot.parsers;

import com.dmitriyabdurazakov.springboot.entity.Category;
import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.entity.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.mappers.ProductMapper;
import com.dmitriyabdurazakov.springboot.service.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductParser {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ProductMapper productMapper;
    private final CategoryService categoryService;

    public List<ProductDTO> getProductDTOList(List<String> paths) {
        List<ProductDTO> productDTOS = new ArrayList<>();
        paths.forEach(path -> {
            try {
                productDTOS.add(objectMapper.readValue(new File(path), ProductDTO.class));
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return productDTOS;
    }

    public List<Product> getProductList(List<String> paths) {
        List<ProductDTO> productDTOList = getProductDTOList(paths);
        List<Product> productList = new ArrayList<>();
        productDTOList.forEach(dto -> {
            Product product = productMapper.mapProductDtoToProduct(dto);
            List<Category> categories = categoryService.findAllById(dto.getCategoriesIds());
            if (categories.size() < dto.getCategoriesIds().size()) {
                throw new RuntimeException("Не найдена одна или несколько категорий с ID " + dto.getCategoriesIds());
            }
            product.setCategories(new HashSet<>(categories));
            productList.add(product);
        });
        return productList;
    }
}

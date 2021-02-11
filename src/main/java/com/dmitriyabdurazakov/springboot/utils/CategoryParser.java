package com.dmitriyabdurazakov.springboot.utils;

import com.dmitriyabdurazakov.springboot.entity.Category;
import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.entity.dto.CategoryDTO;
import com.dmitriyabdurazakov.springboot.entity.dto.ProductDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryParser {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final ModelMapper modelMapper = new ModelMapper();
    private final ProductParser productParser;


    public List<Category> parseCategories(List<String> paths) {
        List<Category> categories = new ArrayList<>();
        paths.forEach(path -> {
            Category category = parseCategory(path);
            categories.add(category);
        });
        return categories;
    }

    @SneakyThrows
    public Category parseCategory(String path) {
        JsonNode jsonNode = objectMapper.readTree(new File(path));
        String products = jsonNode.get("products").toString();
        List<ProductDTO> productDTOS = objectMapper.readValue(products, new TypeReference<>() {
        });
        List<Product> productList = new ArrayList<>();
        productDTOS.forEach(dto -> {
            Product product = productParser.mapDtoToProduct(dto);
            productList.add(product);
        });
        CategoryDTO categoryDTO = objectMapper.readValue(new File(path), CategoryDTO.class);
        Category category = modelMapper.map(categoryDTO, Category.class);
        category.setProducts(productList);
        return category;
    }
}

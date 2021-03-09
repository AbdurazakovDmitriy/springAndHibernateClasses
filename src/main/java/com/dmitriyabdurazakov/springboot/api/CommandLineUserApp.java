package com.dmitriyabdurazakov.springboot.api;


import com.dmitriyabdurazakov.springboot.service.management.CategoryService;
import com.dmitriyabdurazakov.springboot.service.management.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@PropertySource("classpath:resources.properties")
@RequiredArgsConstructor
public class CommandLineUserApp implements CommandLineRunner {
    private final ProductService productService;
    private final CategoryService categoryService;

    @Value("${products}")
    private List<String> productsFiles;

    @Value("${categories}")
    private List<String> categoriesFiles;

    @Override
    public void run(String... args) {
//        categoryService.saveAllCategoriesFromFilePaths(categoriesFiles);
//        productService.saveAllProductsFromFilePaths(productsFiles);
    }
}

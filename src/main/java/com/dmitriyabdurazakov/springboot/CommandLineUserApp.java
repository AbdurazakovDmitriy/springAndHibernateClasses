package com.dmitriyabdurazakov.springboot;


import com.dmitriyabdurazakov.springboot.entity.Category;
import com.dmitriyabdurazakov.springboot.entity.Product;
import com.dmitriyabdurazakov.springboot.service.CategoryService;
import com.dmitriyabdurazakov.springboot.service.ProductService;
import com.dmitriyabdurazakov.springboot.utils.CategoryParser;
import com.dmitriyabdurazakov.springboot.utils.ProductParser;
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
    private final ProductParser productParser;
    private final ProductService productService;

    private final CategoryParser categoryParser;
    private final CategoryService categoryService;

    @Value("${products}")
    private List<String> productsFiles;

    @Value("${categories}")
    private List<String> categoriesFiles;

    @Override
    public void run(String... args) {
        List<Product> products = productParser.parseProducts(productsFiles);
        List<Product> productList = productService.saveAll(products);
        System.out.println(productList);
        //======================================================

        //========================================================
        List<Category> categories = categoryParser.parseCategories(categoriesFiles);
        List<Category> categoryList = categoryService.saveAll(categories);
        System.out.println(categoryList);
    }
}

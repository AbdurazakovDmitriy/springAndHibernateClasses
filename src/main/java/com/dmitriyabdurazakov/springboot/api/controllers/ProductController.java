package com.dmitriyabdurazakov.springboot.api.controllers;

import com.dmitriyabdurazakov.springboot.data.entity.Category;
import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.data.repository.CategoryRepository;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import com.dmitriyabdurazakov.springboot.service.management.ProductService;
import com.dmitriyabdurazakov.springboot.service.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ProductController {
    private final ProductMapper productMapper;
    private final ProductService productService;
    private final CategoryRepository categoryRepository;

    @PostMapping("/products")
    @Transactional
    public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
        Product product = productMapper.mapProductDtoToProduct(productDTO);
        List<Category> categories = categoryRepository.findAllById(productDTO.getCategoriesIds());
        product.addCategories(categories);
        productService.saveProduct(product);
        return productDTO;
    }
}

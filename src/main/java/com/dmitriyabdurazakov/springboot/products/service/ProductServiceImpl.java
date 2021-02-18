package com.dmitriyabdurazakov.springboot.products.service;

import com.dmitriyabdurazakov.springboot.products.entity.Product;
import com.dmitriyabdurazakov.springboot.categories.repositories.CategoryRepository;
import com.dmitriyabdurazakov.springboot.products.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public List<Product> saveAll(List<Product> products) {
        return productRepository.saveAll(products);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }
}

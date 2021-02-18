package com.dmitriyabdurazakov.springboot.products.service;


import com.dmitriyabdurazakov.springboot.products.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> saveAll(List<Product> products);

    Product findById(Long id);

    List<Product> findAll();
}
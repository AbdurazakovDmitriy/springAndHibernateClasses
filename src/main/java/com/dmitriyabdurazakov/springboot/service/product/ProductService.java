package com.dmitriyabdurazakov.springboot.service.product;


import com.dmitriyabdurazakov.springboot.data.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> saveAll(List<Product> products);

    Product findById(Long id);

    List<Product> findAll();

    List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths);
}

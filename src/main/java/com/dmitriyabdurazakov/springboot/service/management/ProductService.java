package com.dmitriyabdurazakov.springboot.service.management;


import com.dmitriyabdurazakov.springboot.data.entity.Product;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    Product saveProduct(Product product, List<Long> categoriesIds);

    List<Product> findAllByName(String name);

    List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths);

    List<Product> findAll(Pageable pageable);

    Product findById(String id);
}

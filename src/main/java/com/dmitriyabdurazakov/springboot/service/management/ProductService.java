package com.dmitriyabdurazakov.springboot.service.management;


import com.dmitriyabdurazakov.springboot.data.entity.Product;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths);

    Product saveProduct(Product product, List<Long> categoriesIds);
}

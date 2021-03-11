package com.dmitriyabdurazakov.springboot.service.management;


import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Product saveProduct(Product product);

    List<Product> findAllByName(String name);

    List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths);

    Page<Product> findAll(Pageable pageable);

    Product findById(String id);

    Product saveProduct(ProductDTO productDTO);
}

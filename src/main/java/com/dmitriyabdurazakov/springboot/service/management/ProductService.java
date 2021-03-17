package com.dmitriyabdurazakov.springboot.service.management;


import com.dmitriyabdurazakov.springboot.data.entity.Product;
import com.dmitriyabdurazakov.springboot.service.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface ProductService {

    List<Product> saveAllProductsFromFilePaths(List<String> productsFilesPaths);

    Product findById(String id);

    Product saveProduct(ProductDTO productDTO);

    Page<Product> findAllByFilter(Specification<Product> specification, Pageable pageable);
}

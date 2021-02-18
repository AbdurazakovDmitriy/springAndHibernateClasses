package com.dmitriyabdurazakov.springboot.products.repositories;

import com.dmitriyabdurazakov.springboot.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

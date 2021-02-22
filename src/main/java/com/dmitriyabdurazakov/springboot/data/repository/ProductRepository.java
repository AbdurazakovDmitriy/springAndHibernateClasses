package com.dmitriyabdurazakov.springboot.data.repository;

import com.dmitriyabdurazakov.springboot.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

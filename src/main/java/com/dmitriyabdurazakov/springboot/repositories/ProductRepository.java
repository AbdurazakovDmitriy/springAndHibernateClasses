package com.dmitriyabdurazakov.springboot.repositories;

import com.dmitriyabdurazakov.springboot.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}

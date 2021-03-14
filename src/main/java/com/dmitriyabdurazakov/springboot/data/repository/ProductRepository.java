package com.dmitriyabdurazakov.springboot.data.repository;

import com.dmitriyabdurazakov.springboot.data.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByName(String name);


// этот варик работает, но в задании есть еще поиск по категории
//    @Query("SELECT p FROM Product p WHERE (:name is null or p.name LIKE %:name%) and (:id is null or p.id = :id)")
//    List<Product> findAllByNameContainingAndId(@Param("name") String name, @Param("id") Long id);

//    а вот так ошибка вылетает
    @Query(value = "SELECT p FROM Product p WHERE " +
        "(:name is null or p.name LIKE %:name%) and " +
        "(:id is null or p.id = :id) and " +
        "(:categoryId is null or p.categories = (SELECT c FROM Category c WHERE c.id = :categoryId))")
    List<Product> findAllByNameContainingAndIdAndCategoryId(@Param("name") String name, @Param("id") Long id, @Param("categoryId") Long categoryId);
}
package com.dmitriyabdurazakov.springboot.entity;

import com.dmitriyabdurazakov.springboot.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Table(name = "category")
@Entity
@Getter
@Setter
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CategoryStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
        name = "product_category",
        joinColumns = @JoinColumn(name = "category_id"),
        inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private Set<Product> products = new HashSet<>();

    public void addProduct(Product product) {
        this.products.add(product);
        product.getCategories().add(this);
    }

    public void addProducts(List<Product> productList) {
        this.products.addAll(productList);
        productList.forEach(product -> product.addCategory(this));
    }
}
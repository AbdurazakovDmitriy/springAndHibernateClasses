package com.dmitriyabdurazakov.springboot.data.entity;

import com.dmitriyabdurazakov.springboot.data.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
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
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private CategoryStatus status;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "category_product",
        joinColumns = {@JoinColumn(name = "category_id")},
        inverseJoinColumns = {@JoinColumn(name = "product_id")})
    private List<Product> products = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "category_supplier",
        joinColumns = {@JoinColumn(name = "category_id")},
        inverseJoinColumns = {@JoinColumn(name = "supplier_id")}
    )
    private Set<Supplier> suppliers = new HashSet<>();
}
package com.dmitriyabdurazakov.springboot.entity;

import com.dmitriyabdurazakov.springboot.enums.CategoryStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "categories", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.MERGE, CascadeType.REFRESH})
    private List<Product> products = new ArrayList<>();
}
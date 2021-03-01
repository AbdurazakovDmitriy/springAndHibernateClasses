package com.dmitriyabdurazakov.springboot.data.entity;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
@Table(name = "product")
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, String> characteristics = new HashMap<>();

    @Column(nullable = false)
    private byte[] image;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<Category> categories = new ArrayList<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void addCategories(List<Category> categoryList) {
        this.categories.addAll(categoryList);
        categoryList.forEach(category -> category.getProducts().add(this));
    }
}

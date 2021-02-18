package com.dmitriyabdurazakov.springboot.suppliers.entity;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "supplier")
@Getter
@Setter
public class Supplier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String alias;

    @ManyToOne(fetch = FetchType.EAGER)
    private Category category;

}

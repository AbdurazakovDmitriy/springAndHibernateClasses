package com.dmitriyabdurazakov.springboot.products.entity;

import com.dmitriyabdurazakov.springboot.categories.entity.Category;
import com.dmitriyabdurazakov.springboot.offers.entity.Offer;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
@Getter
@Setter
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb", nullable = false)
    private Map<String, String> characteristics = new HashMap<>();

    @Column(nullable = false)
    private byte[] image;

    @ManyToMany(mappedBy = "products", fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<Category> categories = new HashSet<>();

    @OneToMany(
        mappedBy = "product",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private Set<Offer> offers = new HashSet<>();

    public void addCategory(Category category) {
        this.categories.add(category);
        category.getProducts().add(this);
    }

    public void addOffer(Offer offer) {
        this.offers.add(offer);
        offer.setProduct(this);
    }
}

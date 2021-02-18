package com.dmitriyabdurazakov.springboot.offers.entity;

import com.dmitriyabdurazakov.springboot.offers.enums.OfferStatus;
import com.dmitriyabdurazakov.springboot.products.entity.Product;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "offer")
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    private OfferStatus status;

    @Column(nullable = false)
    private int weight;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}

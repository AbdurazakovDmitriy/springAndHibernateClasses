package com.dmitriyabdurazakov.springboot.data.entity;

import com.dmitriyabdurazakov.springboot.data.enums.OfferStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "offer")
public class Offer extends RepresentationModel<Offer> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long id;

    @Column(nullable = false)
    private int price;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private OfferStatus status;

    @Column(nullable = false)
    private int weight;

    @ManyToOne(fetch = FetchType.EAGER)
    private Product product;
}

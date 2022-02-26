package com.week1.assignment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "product")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private Double price;
    private Double discount;
    private Double fullPrice;
    private String shop;
    private String image;
    private Date createdAt;
    private String warranty;
    private Date promotionStart;
    private Date promotionEnd;

    @OneToMany(orphanRemoval=true, mappedBy = "product")
    private List<ProductAvailable> productAvailableList;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(orphanRemoval=true, mappedBy = "product")
    public List<BasketItem> basketItems;
}

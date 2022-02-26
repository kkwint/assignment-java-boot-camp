package com.week1.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "basket_item")
@Data @AllArgsConstructor @NoArgsConstructor
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double quantity;
    private Double size;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id", nullable = true)
    private Product product;
}

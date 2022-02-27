package com.week1.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data @AllArgsConstructor @NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double quantity;
    private Double size;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "product_id")
    private Product product;
}

package com.week1.assignment.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "product_available")
@Data @AllArgsConstructor @NoArgsConstructor
public class ProductAvailable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Double size;
    private Double quantity;
    private Date createdAt;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "id")
    private Product product;
}

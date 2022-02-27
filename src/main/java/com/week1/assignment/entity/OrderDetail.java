package com.week1.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_detail")
@Data @AllArgsConstructor @NoArgsConstructor
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(cascade= CascadeType.ALL)
    @JoinColumn(name = "order_detail_id")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;

    private Double total;

    @OneToOne(mappedBy = "orderDetail", cascade = CascadeType.ALL)
    private PaymentDetail paymentDetail;
}

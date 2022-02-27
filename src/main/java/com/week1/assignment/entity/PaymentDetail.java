package com.week1.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "payment_detail")
@Data @AllArgsConstructor @NoArgsConstructor
public class PaymentDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double amount;
    private String paymentType;
    private String cardNo;

    @OneToOne
    @JoinColumn(name = "order_detail_id", unique = true)
    private OrderDetail orderDetail;
}

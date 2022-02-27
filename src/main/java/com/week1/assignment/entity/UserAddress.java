package com.week1.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_address")
@Data @AllArgsConstructor @NoArgsConstructor
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String address;
    private String postCode;
    private String district;
    private String province;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}

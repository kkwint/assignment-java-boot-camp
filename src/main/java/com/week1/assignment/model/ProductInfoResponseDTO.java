package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductInfoResponseDTO {

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
}

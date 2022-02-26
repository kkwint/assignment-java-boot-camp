package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @AllArgsConstructor @NoArgsConstructor
public class ProductAvailableDTO {

    private Integer id;
    private Double size;
    private Double quantity;
    private Date createdAt;
}

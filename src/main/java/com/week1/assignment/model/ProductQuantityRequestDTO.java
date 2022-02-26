package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductQuantityRequestDTO {

    @Id
    private Integer productId;
    private Double quantity;
    private Double size;
}

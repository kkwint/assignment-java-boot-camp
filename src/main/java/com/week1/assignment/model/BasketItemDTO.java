package com.week1.assignment.model;

import com.week1.assignment.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class BasketItemDTO {

    private Integer basketItemId;
    private Double quantity;
    private Double size;
    private ProductSelectedDetailDTO productDetail;
}

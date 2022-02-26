package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class BasketCreateEnquiryResponseDTO {
    private Integer basketId;
    private List<BasketItemDTO> basketItems;
    private Double total;
}

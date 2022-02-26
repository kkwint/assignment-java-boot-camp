package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class BasketEnquiryRequestDTO {

    private String userName;
    private List<BasketDTO> basketList;
}

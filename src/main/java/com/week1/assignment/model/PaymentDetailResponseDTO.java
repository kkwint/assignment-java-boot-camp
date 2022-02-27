package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class PaymentDetailResponseDTO {

    private Integer id;
    private String payment_type;
    private String cardNo;
    private String cardName;
    private String expiryDate;
    private String cvv;

}

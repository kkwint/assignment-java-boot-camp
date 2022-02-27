package com.week1.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data @NoArgsConstructor @AllArgsConstructor
public class ConfirmEnquiryOrderResponseDTO {

    private Integer id;
    private String invoice_no;
    private String payer;
    private String transactionDate;
    private String expiredDate;
    private String payee;
    private String detail;
    private Double total;
}

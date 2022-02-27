package com.week1.assignment.controller;

import com.week1.assignment.model.PaymentDetailResponseDTO;
import com.week1.assignment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/default")
    public PaymentDetailResponseDTO getDefaultPaymentDetail(@RequestParam("userName") String userName) {
        return paymentService.getDefaultPaymentDetail(userName);
    }
}

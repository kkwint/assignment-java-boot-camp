package com.week1.assignment.controller;

import com.week1.assignment.model.ConfirmOrderRequestDTO;
import com.week1.assignment.model.ConfirmEnquiryOrderResponseDTO;
import com.week1.assignment.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/confirm-order")
    public ConfirmEnquiryOrderResponseDTO confirmOder(@RequestBody ConfirmOrderRequestDTO confirmOrderRequestDTO) {
        return orderService.confirmOrder(confirmOrderRequestDTO);
    }

    @GetMapping("/enquiry")
    public ConfirmEnquiryOrderResponseDTO getOrderDetail(@RequestParam("userName") String userName) {
        return orderService.getOrderDetail(userName);
    }
}

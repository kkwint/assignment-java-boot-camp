package com.week1.assignment.controller;

import com.week1.assignment.model.BasketCreateEnquiryResponseDTO;
import com.week1.assignment.model.BasketCreateRequestDTO;
import com.week1.assignment.model.BasketEnquiryRequestDTO;
import com.week1.assignment.model.UserAddressResponseDTO;
import com.week1.assignment.service.BasketService;
import com.week1.assignment.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/shipping")
public class ShippingController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-address")
    public UserAddressResponseDTO getUserAddress(@RequestParam("userName") String userName) {

        return userService.getUserAddress(userName);
    }
}

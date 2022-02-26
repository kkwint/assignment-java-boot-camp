package com.week1.assignment.controller;

import com.week1.assignment.model.BasketCreateEnquiryResponseDTO;
import com.week1.assignment.model.BasketCreateRequestDTO;
import com.week1.assignment.model.BasketEnquiryRequestDTO;
import com.week1.assignment.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {

    @Autowired
    private BasketService basketService;

    @PostMapping("/create")
    public BasketCreateEnquiryResponseDTO createBasket(@RequestBody BasketCreateRequestDTO basketCreateRequest) {

        return basketService.createBasket(basketCreateRequest);
    }

    @PostMapping("/enquiry")
    public List<BasketCreateEnquiryResponseDTO> getBasketData(@RequestBody BasketEnquiryRequestDTO basketEnquiryRequest) {
        return basketService.findBasketListByBasketIdAndUserName(basketEnquiryRequest);
    }
}

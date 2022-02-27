package com.week1.assignment.service;

import com.week1.assignment.entity.User;
import com.week1.assignment.entity.UserPayment;
import com.week1.assignment.model.PaymentDetailResponseDTO;
import com.week1.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private UserRepository userRepository;

    public PaymentDetailResponseDTO getDefaultPaymentDetail(String userName) {

        Optional<User> user = userRepository.findByUserName(userName);

        return transformToPaymentDetailDTO(user.get());
    }

    private PaymentDetailResponseDTO transformToPaymentDetailDTO(User user) {
        PaymentDetailResponseDTO paymentDetailResponse = new PaymentDetailResponseDTO();

        UserPayment defaultUserPayment = user.getUserPaymentList()
                .stream()
                .filter(payment -> payment.getIsDefaultPayment().equals(true))
                .findFirst().get();

        paymentDetailResponse.setId(defaultUserPayment.getId());
        paymentDetailResponse.setPaymentType(defaultUserPayment.getPaymentType());
        paymentDetailResponse.setCardNo(defaultUserPayment.getCardNo());
        paymentDetailResponse.setCardName(defaultUserPayment.getCardName());
        paymentDetailResponse.setExpiryDate(defaultUserPayment.getExpiryDate());
        paymentDetailResponse.setCvv(defaultUserPayment.getCvv());

        return paymentDetailResponse;
    }

}

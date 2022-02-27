package com.week1.assignment.controller;

import com.week1.assignment.entity.User;
import com.week1.assignment.entity.UserAddress;
import com.week1.assignment.entity.UserPayment;
import com.week1.assignment.model.PaymentDetailResponseDTO;
import com.week1.assignment.model.UserAddressResponseDTO;
import com.week1.assignment.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PaymentControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("ส่ง userName = test แล้วจะต้องได้ user ที่มี userName = test และ" +
            "payment_type=DEBIT,card_no=12200110044,card_name=UBI SOFT,expiry=12/12/2025,cvv=555")
    void getDefaultPayment() {

        // Arrange
        User user = new User();
        user.setId(1);
        user.setUserName("test");

        UserPayment userPayment = new UserPayment(1,"DEBIT","12200110044","UBI SOFT","12/12/2025","555",Boolean.TRUE, user);
        List<UserPayment> userPaymentList = new ArrayList<>();
        userPaymentList.add(userPayment);
        user.setUserPaymentList(userPaymentList);

        when(userRepository.findByUserName("test")).thenReturn(Optional.of(user));

        //Act
        PaymentDetailResponseDTO result = testRestTemplate.getForObject("/api/payment/default/?userName=test", PaymentDetailResponseDTO.class);

        //Assert
        PaymentDetailResponseDTO expectedObject = new PaymentDetailResponseDTO(1, "DEBIT", "12200110044","UBI SOFT","12/12/2025","555");
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expectedObject);
    }
}
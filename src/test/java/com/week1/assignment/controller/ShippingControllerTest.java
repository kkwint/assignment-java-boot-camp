package com.week1.assignment.controller;

import com.week1.assignment.entity.User;
import com.week1.assignment.entity.UserAddress;
import com.week1.assignment.model.UserAddressResponseDTO;
import com.week1.assignment.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ShippingControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private UserRepository userRepository;

    @Test
    @DisplayName("ส่ง userName = test แล้วจะต้องได้ user ที่มี userName = test และ" +
            "address=111/11,postcode=3000,district=JKN,province=BANGKOK")
    void getUserAddress() {

        // Arrange
        User user = new User();
        user.setId(1);
        user.setUserName("test");
        user.setUserAddress(new UserAddress(1, "111/11", "3000", "JKN", "BANGKOK", user));
        when(userRepository.findByUserName("test")).thenReturn(Optional.of(user));

        //Act
        UserAddressResponseDTO result = testRestTemplate.getForObject("/api/shipping/user-address/?userName=test", UserAddressResponseDTO.class);

        //Assert
        UserAddressResponseDTO expectedObject = new UserAddressResponseDTO(1,"test",null,null,null,null,"111/11", "3000", "JKN", "BANGKOK");
        assertThat(result)
                .usingRecursiveComparison()
                .isEqualTo(expectedObject);
    }
}
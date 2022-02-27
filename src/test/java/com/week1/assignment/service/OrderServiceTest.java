package com.week1.assignment.service;

import com.week1.assignment.model.BasketItemIdDTO;
import com.week1.assignment.model.ConfirmOrderRequestDTO;
import com.week1.assignment.model.ConfirmEnquiryOrderResponseDTO;
import com.week1.assignment.repository.OrderDetailRepository;
import com.week1.assignment.repository.PayeeRepository;
import com.week1.assignment.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static com.week1.assignment.service.OrderDummy.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private OrderDetailRepository orderDetailRepositoryMock;

    @Mock
    private PayeeRepository payeeRepositoryMock;

    @Test
    @DisplayName("confirm order ด้วย request: " +
            " userName = test, " +
            " basketItemId = 1 " +
            " แล้วจะต้องได้ basketResponse ที่ถูกต้อง")
    void caseConfirmOrder() {
        // Arrange

        when(userRepositoryMock.findByUserName(any())).thenReturn(Optional.of(getUser()));
        when(orderDetailRepositoryMock.save(any())).thenReturn(getOrderDetail());
        when(payeeRepositoryMock.findById(any())).thenReturn(Optional.of(getPayee()));

        // Act
        OrderService basketService = new OrderService(userRepositoryMock,orderDetailRepositoryMock,payeeRepositoryMock);
        ConfirmOrderRequestDTO confirmOrderRequestDTO = new ConfirmOrderRequestDTO("test", List.of(new BasketItemIdDTO(1)));
        ConfirmEnquiryOrderResponseDTO confirmOrderResponse = basketService.confirmOrder(confirmOrderRequestDTO);

        // Assert
        assertThat(confirmOrderResponse)
                .usingRecursiveComparison();
        assertEquals(getConfirmOrderResponseDTO().getPayer(),confirmOrderResponse.getPayer());
        assertEquals(getConfirmOrderResponseDTO().getPayee(),confirmOrderResponse.getPayee());
        assertEquals(getConfirmOrderResponseDTO().getTotal(),confirmOrderResponse.getTotal());
    }
}
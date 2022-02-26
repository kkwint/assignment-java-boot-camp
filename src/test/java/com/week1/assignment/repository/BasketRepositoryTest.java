package com.week1.assignment.repository;

import com.week1.assignment.entity.Basket;
import com.week1.assignment.entity.BasketItem;
import com.week1.assignment.entity.Product;
import com.week1.assignment.entity.User;
import com.week1.assignment.model.ProductInfoResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
class BasketRepositoryTest {

    @Autowired
    private BasketRepository basketRepository;

    @Test
    @DisplayName("ส่ง userName = test, basketId = 1 แล้วจะต้องได้รับ basket ที่มี userName = test, basketId = 1")
    void findByBasketIdAndUserName() {
        // Arrange
        Basket basket = new Basket();

        User user = new User();
        user.setUserName("test");

        List<BasketItem> basketItems = new ArrayList<>();
        BasketItem basketItem = new BasketItem();
        basketItem.setQuantity(1.0);
        basketItem.setSize(1.0);
        basketItems.add(basketItem);

        basket.setBasketItem(basketItems);
        basket.setTotal(1.0);
        basket.setUser(user);

        basketRepository.save(basket);

        // Act
        List<Integer> basketIds = new ArrayList<>(){{add(1);}};
        List<Basket> result = basketRepository.findByBasketIdAndUserName(basketIds, "test");

        // Assert
        assertEquals(result.size(),1);
        assertEquals(result.get(0).getUser().getUserName(),"test");
        assertEquals(result.get(0).getId(),1);
    }
}
package com.week1.assignment.repository;

import com.week1.assignment.entity.Basket;
import com.week1.assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BasketRepository extends JpaRepository<Basket, Integer> {

    @Query("select b from Basket b where b.id in :basketId and b.user.userName =:userName" )
    List<Basket> findByBasketIdAndUserName(@Param("basketId")List<Integer> basketId, @Param("userName")String userName);
}

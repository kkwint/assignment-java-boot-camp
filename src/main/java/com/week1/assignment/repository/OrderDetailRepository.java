package com.week1.assignment.repository;

import com.week1.assignment.entity.Basket;
import com.week1.assignment.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("select o from OrderDetail o where o.user.userName =:userName" )
    List<OrderDetail> findByUserName(@Param("userName")String userName);
}

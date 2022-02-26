package com.week1.assignment.repository;

import com.week1.assignment.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByNameContainingIgnoreCase(String name);

    @Query("select p from Product p where p.id in :ids" )
    List<Product> findByIds(@Param("ids") List<Integer> ids);
}

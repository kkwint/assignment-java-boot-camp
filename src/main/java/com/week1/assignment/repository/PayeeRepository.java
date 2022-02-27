package com.week1.assignment.repository;

import com.week1.assignment.entity.Payee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayeeRepository extends JpaRepository<Payee, Integer> {
}

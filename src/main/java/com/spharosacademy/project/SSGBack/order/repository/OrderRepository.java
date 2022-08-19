package com.spharosacademy.project.SSGBack.order.repository;

import com.spharosacademy.project.SSGBack.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Long> {

//    List<Orders> findAllByUserId(Long userId);
}

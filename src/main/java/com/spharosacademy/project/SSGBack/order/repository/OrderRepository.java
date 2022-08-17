package com.spharosacademy.project.SSGBack.order.repository;

import com.spharosacademy.project.SSGBack.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

    Orders findByUserId(Long userId);
}

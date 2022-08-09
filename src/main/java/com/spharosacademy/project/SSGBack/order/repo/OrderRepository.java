package com.spharosacademy.project.SSGBack.order.repo;

import com.spharosacademy.project.SSGBack.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}

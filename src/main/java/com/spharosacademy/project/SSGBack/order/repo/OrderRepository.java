package com.spharosacademy.project.SSGBack.order.repo;

import com.spharosacademy.project.SSGBack.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}

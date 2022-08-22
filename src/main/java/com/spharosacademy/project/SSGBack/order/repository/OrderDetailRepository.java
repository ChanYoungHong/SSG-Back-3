package com.spharosacademy.project.SSGBack.order.repository;

import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {
}

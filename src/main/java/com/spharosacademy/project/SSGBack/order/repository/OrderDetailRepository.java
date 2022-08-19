package com.spharosacademy.project.SSGBack.order.repository;

import com.spharosacademy.project.SSGBack.order.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    List<OrderDetail> findAllByUserId(Long userId);

}

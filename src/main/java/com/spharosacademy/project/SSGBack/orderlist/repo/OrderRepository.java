package com.spharosacademy.project.SSGBack.orderlist.repo;

import com.spharosacademy.project.SSGBack.orderlist.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}

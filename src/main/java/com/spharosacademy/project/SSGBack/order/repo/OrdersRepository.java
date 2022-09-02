package com.spharosacademy.project.SSGBack.order.repo;

import com.spharosacademy.project.SSGBack.order.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {

    
}

package com.spharosacademy.project.SSGBack.orderlist.repo;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptionDto;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderListRepository extends JpaRepository<OrderList, Long> {



}


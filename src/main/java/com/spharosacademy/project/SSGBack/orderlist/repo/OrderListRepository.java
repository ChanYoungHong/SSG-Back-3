package com.spharosacademy.project.SSGBack.orderlist.repo;

import com.spharosacademy.project.SSGBack.order.dto.response.OrdersOutputDto;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.orderlist.entity.OrderList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderListRepository extends JpaRepository<OrderList, Long> {

    List<OrderList> findAllByMemberId(Long userId);
    List<OrderList> findAllByOrders(Orders orders);

    @Query(value = "select o.orderId from OrderList o where o.memberId=:userId")
    List<Long> getOrderId(Long userId);

}
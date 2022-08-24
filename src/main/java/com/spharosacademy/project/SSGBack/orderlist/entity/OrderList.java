package com.spharosacademy.project.SSGBack.orderlist.entity;

import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.user.entity.BaseEntity;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Table(name = "order_list")
public class OrderList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long userId;

    @Column(name = "order_state")
    private Boolean orderState;

    @Column(name = "order_msg")
    private String orderMsg;

    @Column(name = "order_decidedDate")
    private LocalDateTime orderDecidedDate;

    @Column(name = "order_receiver")
    private String orderReceiver;

    @Column(name = "order_AnOrderer")
    private String orderAnOrderer;

    @Column(name = "user_address")
    private String userAddress;

    @Column(name = "option_id")
    private Long optionId;

    @Column(name = "member_id")
    private Long memberId;

    private int qty;

    private String userPhoneNumber;
    private String userEmail;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;

}

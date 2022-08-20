package com.spharosacademy.project.SSGBack.orderlist.entity;

import com.spharosacademy.project.SSGBack.Cart;
import com.spharosacademy.project.SSGBack.Product;
import com.spharosacademy.project.SSGBack.order.entity.Orders;
import com.spharosacademy.project.SSGBack.user.entity.BaseEntity;
import com.spharosacademy.project.SSGBack.user.entity.User;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
@Table(name = "order_list") // Order이라는 어노테이션이 있어서 OrderList로 표기함
public class OrderList extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_list_id")
    private Long orderListId;

//    @Column(name = "order_state")
//    private Boolean orderState;

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

    private Long qty;

    private String userPhoneNumber;
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Orders orders;

}

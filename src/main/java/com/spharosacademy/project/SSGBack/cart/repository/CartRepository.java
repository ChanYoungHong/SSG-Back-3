package com.spharosacademy.project.SSGBack.cart.repository;

import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    //사용자 id에 따른 장바구니를 전부 가져온다
    List<Cart> findByUserId(Long userid);

}

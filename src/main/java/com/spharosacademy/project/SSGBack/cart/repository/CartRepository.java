package com.spharosacademy.project.SSGBack.cart.repository;

import com.spharosacademy.project.SSGBack.cart.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    //사용자 id에 따른 장바구니를 전부 가져온다
    List<Cart> findByUserId(Long userid);

    long countByUserId(Long userid);

    boolean existsByUserUserIdAndOptionIdAndProductId(Long userId ,Long optionId, Long productId);

    Cart existsByUserIdAndOptionId(Long userId, Long optionId);


}

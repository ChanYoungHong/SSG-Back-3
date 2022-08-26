package com.spharosacademy.project.SSGBack.wishlist.repository;

import com.spharosacademy.project.SSGBack.wishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUserId(Long userid);

    @Query(value = "select w.id from WishList w where w.user.id=:userId and w.product.id=:productId")
    Long findByUserIdAndProductId(Long userId, Long productId);
}

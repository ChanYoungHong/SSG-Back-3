package com.spharosacademy.project.SSGBack.wishlist.repository;

import com.spharosacademy.project.SSGBack.wishlist.entity.WishList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WishListRepository extends JpaRepository<WishList, Long> {

    List<WishList> findByUserId(Long userid);
}

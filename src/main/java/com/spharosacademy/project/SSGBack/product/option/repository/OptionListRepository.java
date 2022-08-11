package com.spharosacademy.project.SSGBack.product.option.repository;

import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionListRepository extends JpaRepository<OptionList, Long> {
    List<OptionList> findAllByProductId(Long productId);
}

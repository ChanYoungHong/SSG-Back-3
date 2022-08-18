package com.spharosacademy.project.SSGBack.tempoptionlist;

import com.spharosacademy.project.SSGBack.order.dto.request.OrdersOptionDto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OptionListRepository extends JpaRepository<OptionList, Long> {

    @Query(value = "select m from OptionList m where m.colorId = :colorId and m.siezeId = :sizeId ")
    OptionList findByColorIdAndsizeId(@Param("colorId") Long colorId, @Param("sizeId") Long sizeId);

}

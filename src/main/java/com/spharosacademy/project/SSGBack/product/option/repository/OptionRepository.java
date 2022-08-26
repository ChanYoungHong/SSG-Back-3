package com.spharosacademy.project.SSGBack.product.option.repository;

import com.spharosacademy.project.SSGBack.product.entity.Product;
import com.spharosacademy.project.SSGBack.product.option.dto.output.ColorOutputDto;
import com.spharosacademy.project.SSGBack.product.option.dto.output.SizeOutputDto;
import com.spharosacademy.project.SSGBack.product.option.entity.OptionList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OptionRepository extends JpaRepository<OptionList, Long> {

    List<OptionList> findAllByProduct(Product product);

    OptionList findByColors_IdAndSize_Id(Long colorId, Long sizeId);

    List<OptionList> findByProductId(Long productId);

    @Query(value = "select distinct o.colors.id as colorsId, o.colors.name as colorsName from OptionList o where o.product.id =:productId")
    List<ColorOutputDto> getColorId(@Param("productId") Long productId);

    @Query(value = "select DISTINCT o.size.id as sizeId, o.size.type  as size from OptionList o where o.product.id =:productId")
    List<SizeOutputDto> getSizeId(@Param("productId") Long productId);
}
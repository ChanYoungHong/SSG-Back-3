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

//    OptionList findByColors_IdAndSize_Id(Long colorId, Long sizeId);

//    List<OptionList> findByProductId(Long productId);

    @Query(value = "select distinct o.colors.id as id, o.colors.name as name from OptionList o where o.product.id =:productId")
    List<ColorOutputDto> getColorId(@Param("productId") Long productId);

    @Query(value = "select o.id as optionId, o.size.id as id, o.size.type as name, o.stock as stock from OptionList o where o.colors.id =:colorId and o.product.id =:productId")
    List<SizeOutputDto> getSizeId(@Param("productId") Long productId, @Param("colorId") Long colorId);

    @Query(value = "select o.id from OptionList o where o.product.id =:productId")
    List<Long> getOptionId(@Param("productId") Long productId);
}
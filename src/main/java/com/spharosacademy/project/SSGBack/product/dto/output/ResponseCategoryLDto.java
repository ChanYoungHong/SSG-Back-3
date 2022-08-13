package com.spharosacademy.project.SSGBack.product.dto.output;
;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseCategoryLDto {

    private int id;
    private String name;
    List<ResponseCategoryMDto> responseCategoryMDtos;
}

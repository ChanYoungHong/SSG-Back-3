package com.spharosacademy.project.SSGBack.product.option.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.Nullable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionInputDto {

    private Long colorId;
    private Long sizeId;
    private int stock;
}

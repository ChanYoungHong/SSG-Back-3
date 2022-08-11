package com.spharosacademy.project.SSGBack.product.option.sevice;

import com.spharosacademy.project.SSGBack.product.option.dto.CreateColorDto;
import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;

public interface ColorService {

    ColorOption createColor(CreateColorDto createColorDto);
}

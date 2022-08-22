package com.spharosacademy.project.SSGBack.product.option.service;

import com.spharosacademy.project.SSGBack.product.option.dto.input.RequestColorDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Colors;

public interface ColorService {

    Colors addColor(RequestColorDto requestColorDto);
}

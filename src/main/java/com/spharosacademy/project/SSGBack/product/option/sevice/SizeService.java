package com.spharosacademy.project.SSGBack.product.option.sevice;

import com.spharosacademy.project.SSGBack.product.option.dto.CreateSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.SizeOption;

public interface SizeService {

    SizeOption createSize(CreateSizeDto createSizeDto);
}

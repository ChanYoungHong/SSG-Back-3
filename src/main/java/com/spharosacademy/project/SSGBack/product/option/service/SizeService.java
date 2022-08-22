package com.spharosacademy.project.SSGBack.product.option.service;

import com.spharosacademy.project.SSGBack.product.option.dto.input.RequestSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Size;

public interface SizeService {

    Size addSize(RequestSizeDto requestSizeDto);
}

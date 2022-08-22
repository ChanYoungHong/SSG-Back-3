package com.spharosacademy.project.SSGBack.product.option.service.impl;

import com.spharosacademy.project.SSGBack.product.option.dto.input.RequestSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Size;
import com.spharosacademy.project.SSGBack.product.option.repository.SizeRepository;
import com.spharosacademy.project.SSGBack.product.option.service.SizeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SizeServiceImple implements SizeService {

    private final SizeRepository sizeRepository;

    @Override
    public Size addSize(RequestSizeDto requestSizeDto) {
        return sizeRepository.save(Size.builder()
                .type(requestSizeDto.getSize())
                .build());
    }
}

package com.spharosacademy.project.SSGBack.product.option.service.impl;

import com.spharosacademy.project.SSGBack.product.option.dto.input.RequestColorDto;
import com.spharosacademy.project.SSGBack.product.option.entity.Colors;
import com.spharosacademy.project.SSGBack.product.option.repository.ColorRepository;
import com.spharosacademy.project.SSGBack.product.option.service.ColorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ColorServiceImple implements ColorService {

    private final ColorRepository colorRepository;

    @Override
    public Colors addColor(RequestColorDto requestColorDto) {
        return colorRepository.save(Colors.builder()
                .name(requestColorDto.getColor())
                .build());
    }
}

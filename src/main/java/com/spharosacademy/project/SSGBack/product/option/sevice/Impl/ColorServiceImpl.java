package com.spharosacademy.project.SSGBack.product.option.sevice.Impl;

import com.spharosacademy.project.SSGBack.product.option.dto.CreateColorDto;
import com.spharosacademy.project.SSGBack.product.option.entity.ColorOption;
import com.spharosacademy.project.SSGBack.product.option.repository.ColorOptionRepository;
import com.spharosacademy.project.SSGBack.product.option.sevice.ColorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ColorServiceImpl implements ColorService {
    private final ColorOptionRepository colorOptionRepository;

    @Override
    public ColorOption createColor(CreateColorDto createColorDto) {
        return colorOptionRepository.save(
                ColorOption.builder()
                        .colorType(createColorDto.getName())
                        .build()
        );
    }
}

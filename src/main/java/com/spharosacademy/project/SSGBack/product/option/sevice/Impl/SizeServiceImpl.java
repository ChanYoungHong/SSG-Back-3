package com.spharosacademy.project.SSGBack.product.option.sevice.Impl;

import com.spharosacademy.project.SSGBack.product.option.dto.CreateSizeDto;
import com.spharosacademy.project.SSGBack.product.option.entity.SizeOption;
import com.spharosacademy.project.SSGBack.product.option.repository.SizeOptionRepository;
import com.spharosacademy.project.SSGBack.product.option.sevice.SizeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class SizeServiceImpl implements SizeService {
    private final SizeOptionRepository sizeOptionRepository;

    @Override
    public SizeOption createSize(CreateSizeDto createSizeDto) {
        return sizeOptionRepository.save(
                SizeOption.builder()
                        .sizeType(createSizeDto.getSize())
                        .build()
        );
    }
}

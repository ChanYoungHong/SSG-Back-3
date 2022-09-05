package com.spharosacademy.project.SSGBack.recentWatch.service;

import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentProductDto;
import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentQueryDto;

import java.util.List;

public interface RecentWatchService {
    List<ResponseRecentProductDto> getAllRecentProduct(Long userId);

    List<ResponseRecentQueryDto> getAllRecentQuery(Long userId);

    void deleteProduct(Long id, Long userId);

    void deleteQuery(Long id, Long userId);
}

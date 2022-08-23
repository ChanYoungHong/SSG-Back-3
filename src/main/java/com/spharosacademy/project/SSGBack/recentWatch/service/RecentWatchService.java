package com.spharosacademy.project.SSGBack.recentWatch.service;

import com.spharosacademy.project.SSGBack.recentWatch.dto.response.ResponseRecentProductDto;

import java.util.List;

public interface RecentWatchService {

    List<ResponseRecentProductDto> getAllRecentProduct(Long userId);
}

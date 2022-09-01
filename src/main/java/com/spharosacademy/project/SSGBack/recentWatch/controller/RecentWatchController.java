package com.spharosacademy.project.SSGBack.recentWatch.controller;

import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentProductDto;
import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentQueryDto;
import com.spharosacademy.project.SSGBack.recentWatch.service.RecentWatchService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user/recent")
@RequiredArgsConstructor
public class RecentWatchController {

    private final RecentWatchService recentWatchService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/product")
    public List<ResponseRecentProductDto> getAllRecentProduct(){
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return recentWatchService.getAllRecentProduct(userId);
    }

    @GetMapping("/query")
    public List<ResponseRecentQueryDto> getAllRecentQuery(){
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return recentWatchService.getAllRecentQuery(userId);
    }

}

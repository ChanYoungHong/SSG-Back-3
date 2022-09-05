package com.spharosacademy.project.SSGBack.recentWatch.controller;

import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentProductDto;
import com.spharosacademy.project.SSGBack.recentWatch.dto.ResponseRecentQueryDto;
import com.spharosacademy.project.SSGBack.recentWatch.service.RecentWatchService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recent/user")
@RequiredArgsConstructor
public class RecentWatchController {

    private final RecentWatchService recentWatchService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/product")
    public List<ResponseRecentProductDto> getAllRecentProduct() {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return recentWatchService.getAllRecentProduct(userId);
    }

    @GetMapping("/query")
    public List<ResponseRecentQueryDto> getAllRecentQuery() {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return recentWatchService.getAllRecentQuery(userId);
    }

    @DeleteMapping("/product/{id}")
    public String deleteRecentProduct(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        recentWatchService.deleteProduct(id, userId);
        return "삭제 완료!";
    }

    @DeleteMapping("/query/{id}")
    public String deleteRecentQuery(@PathVariable Long id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        recentWatchService.deleteQuery(id, userId);
        return "삭제 완료!";
    }

}

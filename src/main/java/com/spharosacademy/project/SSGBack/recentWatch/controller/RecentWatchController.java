package com.spharosacademy.project.SSGBack.recentWatch.controller;

import com.spharosacademy.project.SSGBack.recentWatch.dto.response.ResponseRecentProductDto;
import com.spharosacademy.project.SSGBack.recentWatch.service.RecentWatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recentWatch")
@RequiredArgsConstructor
public class RecentWatchController {

    private final RecentWatchService recentWatchService;
    @GetMapping("/{userId}")
    public List<ResponseRecentProductDto> getAllRecentProduct(@PathVariable Long userId){
        return recentWatchService.getAllRecentProduct(userId);
    }
}

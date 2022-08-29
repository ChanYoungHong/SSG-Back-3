package com.spharosacademy.project.SSGBack.category.controller;

import com.spharosacademy.project.SSGBack.category.dto.output.CategoryMDto;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryMDto;
import com.spharosacademy.project.SSGBack.category.service.CategoryMService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateM")
@RequiredArgsConstructor
@CrossOrigin
public class CategoryMController {

    private final CategoryMService categoryMService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public CategoryM addCategoryM(@RequestBody RequestCategoryMDto categoryMDto) {
        return categoryMService.addCategoryM(categoryMDto);
    }

    @GetMapping("/getAll")
    public List<CategoryM> getAll() {
        return categoryMService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryMById(@PathVariable Integer id) {
        categoryMService.deleteCategoryMById(id);
    }

    @GetMapping("/user/get/{id}")
    public CategoryMDto categoryM(@PathVariable Integer id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return categoryMService.getCategoryMById(id, userId);
    }

    @GetMapping("nonmember/get/{id}")
    public CategoryMDto categoryMDto(@PathVariable Integer id) {
        Long userId = -1L;
        return categoryMService.getCategoryMById(id, userId);
    }

    @PutMapping("/edit")
    public CategoryM editCategoryM(@RequestBody RequestCategoryMDto categoryMDto) {
        return categoryMService.editCategoryM(categoryMDto);
    }
}

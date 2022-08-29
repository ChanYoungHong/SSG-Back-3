package com.spharosacademy.project.SSGBack.category.controller;

import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategorySSDto;
import com.spharosacademy.project.SSGBack.category.dto.output.CategorySSDto;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import com.spharosacademy.project.SSGBack.category.service.CategorySSService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateSS")
@RequiredArgsConstructor
@CrossOrigin
public class CategorySSController {
    private final CategorySSService categorySSService;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/add")
    public CategorySS addCategorySS(@RequestBody RequestCategorySSDto categorySSDto) {
        return categorySSService.addCategorySS(categorySSDto);
    }

    @GetMapping("/getAll")
    public List<CategorySS> getAll() {
        return categorySSService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategorySSById(@PathVariable Integer id){
        categorySSService.deleteCategorySSById(id);
    }

    @GetMapping("/user/get/{id}")
    public CategorySSDto categorySS(@PathVariable Integer id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return categorySSService.getCategorySSById(id, userId);
    }

    @GetMapping("nonmember/get/{id}")
    public CategorySSDto categorySSDto(@PathVariable Integer id){
        Long userId = -1L;
        return categorySSService.getCategorySSById(id, userId);
    }

    @PutMapping("/edit")
    public CategorySS editCategorySS(@RequestBody RequestCategorySSDto categorySSDto) {
        return categorySSService.editCategorySS(categorySSDto);
    }
}

package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.dto.input.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.dto.input.CategorySSDto;
import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.entity.CategorySS;
import com.spharosacademy.project.SSGBack.product.service.CategorySSService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateSS")
@RequiredArgsConstructor
public class CategorySSController {
    private final CategorySSService categorySSService;

    @PostMapping("/add")
    public CategorySS addCategorySS(@RequestBody CategorySSDto categorySSDto) {
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

    @GetMapping("/get/{id}")
    public CategorySS categorySS(@PathVariable Integer id) {
        return categorySSService.getCategorySSById(id);
    }

    @PutMapping("/edit/{id}")
    public CategorySS editCategorySS(@PathVariable Integer id, @RequestBody CategorySSDto categorySSDto) {
        return categorySSService.editCategorySS(id, categorySSDto);
    }
}

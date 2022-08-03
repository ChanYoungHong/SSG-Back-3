package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.dto.input.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.entity.CategoryM;
import com.spharosacademy.project.SSGBack.product.dto.input.CategoryMDto;
import com.spharosacademy.project.SSGBack.product.service.CategoryMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateM")
@RequiredArgsConstructor
public class CategoryMController {

    private final CategoryMService categoryMService;

    @PostMapping("/add")
    public CategoryM addCategoryM(@RequestBody CategoryMDto categoryMDto){
        return categoryMService.addCategoryM(categoryMDto);
    }

    @GetMapping("/getAll")
    public List<CategoryM> getAll(){
        return categoryMService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryMById(@PathVariable Integer id){
        categoryMService.deleteCategoryMById(id);
    }

    @GetMapping("/get/{id}")
    public CategoryM categoryM(@PathVariable Integer id) {
        return categoryMService.getCategoryMById(id);
    }

    @PutMapping("/edit/{id}")
    public CategoryM editCategoryM(@RequestBody CategoryMDto categoryMDto) {
        return categoryMService.editCategoryM(categoryMDto.getId(), categoryMDto);
    }
}

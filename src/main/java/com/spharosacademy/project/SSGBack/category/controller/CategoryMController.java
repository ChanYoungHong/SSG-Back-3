package com.spharosacademy.project.SSGBack.category.controller;

import com.spharosacademy.project.SSGBack.category.dto.output.CategoryMDto;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategoryMDto;
import com.spharosacademy.project.SSGBack.category.service.CategoryMService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateM")
@RequiredArgsConstructor
public class CategoryMController {

//    private fina UserRepository user

    private final CategoryMService categoryMService;

    @PostMapping("/add")
    public CategoryM addCategoryM(@RequestBody RequestCategoryMDto categoryMDto){
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
    public CategoryMDto categoryM(@PathVariable Integer id) {
        return categoryMService.getCategoryMById(id);
    }

    @PutMapping("/edit")
    public CategoryM editCategoryM(@RequestBody RequestCategoryMDto categoryMDto) {
        return categoryMService.editCategoryM(categoryMDto);
    }
}

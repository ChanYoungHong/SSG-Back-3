package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.dto.input.CategoryLDto;
import com.spharosacademy.project.SSGBack.product.service.CategoryLService;
import com.spharosacademy.project.SSGBack.product.service.imple.CategoryLServiceimple;
import com.spharosacademy.project.SSGBack.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateL")
@RequiredArgsConstructor
public class CategoryLController {

    private final CategoryLService categoryLService;


    @PostMapping("/add")
    public CategoryL addCategoryL(@RequestBody CategoryLDto categoryLDto) {
        return categoryLService.addCategoryL(categoryLDto);
    }

    @GetMapping("/getAll")
    public List<CategoryL> getAll() {
        return categoryLService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategoryLById(@PathVariable Integer id) {
        categoryLService.deleteCategoryLById(id);
    }

    @GetMapping("/get/{id}")
    public CategoryL categoryL(@PathVariable Integer id) {
        return categoryLService.getCategoryLById(id);
    }

    @PutMapping("/edit/{id}")
    public CategoryL editCategoryLById(@PathVariable Integer id, @RequestBody CategoryLDto categoryLDto) {
        return categoryLService.editCategoryL(id, categoryLDto);
    }
}

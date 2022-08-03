package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.product.entity.CategoryL;
import com.spharosacademy.project.SSGBack.product.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.input.CategorySDto;
import com.spharosacademy.project.SSGBack.product.service.CategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateS")
@RequiredArgsConstructor
public class CategorySController {
    private final CategorySService categorySService;

    @PostMapping("/add")
    public CategoryS addCategoryS(@RequestBody CategorySDto CategorySDto) {
        return categorySService.addCategoryS(CategorySDto);
    }

    @GetMapping("/getAll")
    public List<CategoryS> getAll() {
        return categorySService.getAll();
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCategorySById(@PathVariable Integer id){
        categorySService.deleteCategorySById(id);
    }

    @GetMapping("/get/{id}")
    public CategoryS categoryS(@PathVariable Integer id) {
        return categorySService.getCategorySById(id);
    }

    @PutMapping("/edit/{id}")
    public CategoryS editCategoryS(@RequestBody CategorySDto categorySDto) {
        return categorySService.editCategoryS(categorySDto.getId(), categorySDto);
    }
}

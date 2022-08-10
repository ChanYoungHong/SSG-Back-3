package com.spharosacademy.project.SSGBack.product.controller;

import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.product.dto.input.RequestCategorySDto;
import com.spharosacademy.project.SSGBack.category.service.CategorySService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateS")
@RequiredArgsConstructor
public class CategorySController {
    private final CategorySService categorySService;

    @PostMapping("/add")
    public CategoryS addCategoryS(@RequestBody RequestCategorySDto CategorySDto) {
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

    @PutMapping("/edit")
    public CategoryS editCategoryS(@RequestBody RequestCategorySDto categorySDto) {
        return categorySService.editCategoryS(categorySDto);
    }
}

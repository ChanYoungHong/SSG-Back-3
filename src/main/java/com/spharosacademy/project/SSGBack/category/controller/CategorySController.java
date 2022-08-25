package com.spharosacademy.project.SSGBack.category.controller;

import com.spharosacademy.project.SSGBack.category.dto.output.CategorySDto;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.dto.input.RequestCategorySDto;
import com.spharosacademy.project.SSGBack.category.service.CategorySService;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/CateS")
@RequiredArgsConstructor
public class CategorySController {

    private final CategorySService categorySService;
    private final JwtTokenProvider jwtTokenProvider;

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

    @GetMapping("/user/get/{id}")
    public CategorySDto categoryS(@PathVariable Integer id) {
        String token = jwtTokenProvider.customResolveToken();
        Long userId = Long.valueOf(jwtTokenProvider.getUserPk(token));
        return categorySService.getCategorySById(id, userId);
    }

    @GetMapping("/nonmember/get/{id}")
    public CategorySDto categorySDto(@PathVariable Integer id){
        Long userId = -1L;
        return categorySService.getCategorySById(id, userId);
    }

    @PutMapping("/edit")
    public CategoryS editCategoryS(@RequestBody RequestCategorySDto categorySDto) {
        return categorySService.editCategoryS(categorySDto);
    }
}

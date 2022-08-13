package com.spharosacademy.project.SSGBack.category.dto.input;

import com.spharosacademy.project.SSGBack.category.entity.CategoryL;
import com.spharosacademy.project.SSGBack.category.entity.CategoryM;
import com.spharosacademy.project.SSGBack.category.entity.CategoryS;
import com.spharosacademy.project.SSGBack.category.entity.CategorySS;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateCategoryListDto {

    List<CategoryL> categoryLLists;
    List<CategoryM> categoryMLists;
    List<CategoryS> categorySLists;
    List<CategorySS> categorySSLists;
}

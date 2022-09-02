package com.spharosacademy.project.SSGBack.category.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RequestCategoryMDto {

    private int id;
    private String name;
    private int categoryLId;
}

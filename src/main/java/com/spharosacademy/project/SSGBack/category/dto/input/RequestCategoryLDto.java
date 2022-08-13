package com.spharosacademy.project.SSGBack.category.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategoryLDto {

    private int id;
    private String name;
}

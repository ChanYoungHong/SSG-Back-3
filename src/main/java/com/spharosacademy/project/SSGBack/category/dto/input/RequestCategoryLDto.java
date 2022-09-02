package com.spharosacademy.project.SSGBack.category.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestCategoryLDto {

    private int id;
    private String name;

}

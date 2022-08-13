package com.spharosacademy.project.SSGBack.category.dto.input;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestCategorySSDto {

    private int id;
    private String name;
    private int CategorySId;

}

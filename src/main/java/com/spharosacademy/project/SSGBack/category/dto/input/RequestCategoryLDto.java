<<<<<<<< HEAD:src/main/java/com/spharosacademy/project/SSGBack/user/dto/request/UserEditInputDto.java
package com.spharosacademy.project.SSGBack.user.dto.request;

========
package com.spharosacademy.project.SSGBack.category.dto.input;
>>>>>>>> origin/feature/develop:src/main/java/com/spharosacademy/project/SSGBack/category/dto/input/RequestCategoryLDto.java

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserEditInputDto {

    private String userPhoneNumber;
    private String userEmail;

}

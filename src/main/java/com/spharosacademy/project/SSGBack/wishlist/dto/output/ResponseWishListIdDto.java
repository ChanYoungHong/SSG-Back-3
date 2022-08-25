package com.spharosacademy.project.SSGBack.wishlist.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseWishListIdDto {
    private Long wishListId;
    private Long productId;
}

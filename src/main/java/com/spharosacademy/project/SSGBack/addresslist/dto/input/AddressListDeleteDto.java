package com.spharosacademy.project.SSGBack.addresslist.dto.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressListDeleteDto {
    private int addressId;
}


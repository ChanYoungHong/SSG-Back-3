package com.spharosacademy.project.SSGBack.addresslist.dto.output;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressListOutputDto {

    private Long userId;
    private int addressId;
    private String addressNickname;
    private String addressReceiver;
    private int addressPhoneNumber;
    private int addressHomeNumber;
}

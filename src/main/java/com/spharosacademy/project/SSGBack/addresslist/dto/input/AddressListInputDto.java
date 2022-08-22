package com.spharosacademy.project.SSGBack.addresslist.dto.input;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressListInputDto {

    private Long userId;
    private String addressNickname;
    private String addressReceiver;
    private String addressPhoneNumber;
    private String addressHomeNumber;
    private int addressStatus;




}

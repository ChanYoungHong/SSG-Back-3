package com.spharosacademy.project.SSGBack.address.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder


public class AddressListDto {

    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String addressNickname;
    private String addressReceiver;
    private String addressPhoneNumber;
    private String addressHomeNumber;
    private boolean addressStatus;

    //DTO에 KEY 값 추가 해야 되나?

}

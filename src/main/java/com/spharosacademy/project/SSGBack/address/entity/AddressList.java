package com.spharosacademy.project.SSGBack.address.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int addressId;
    private String addressNickname;
    private String addressReceiver;
    private String addressPhoneNumber;
    private String addressHomeNumber;
    private boolean addressStatus;
    // member 조인
//    @ManyToOne
//    @JoinColumn(name = "member_id")
//    private Member member
}

package com.spharosacademy.project.SSGBack.addresslist.entity;

import com.spharosacademy.project.SSGBack.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor

public class AddressList {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String addressNickname;
    private String addressReceiver;
    private String addressPhoneNumber;
    private String addressHomeNumber;
    private int addressStatus;

    @ManyToOne
    User user;
}

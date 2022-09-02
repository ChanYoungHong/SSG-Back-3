package com.spharosacademy.project.SSGBack.addresslist.entity;
import com.spharosacademy.project.SSGBack.user.entity.User;
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

    @Column(nullable = false)
    private String addressNickname;

    @Column(nullable = false)
    private String addressReceiver;

    @Column(length = 7, nullable = false)
    private int addressPhoneNumber;

    @Column(length = 7)
    private int addressHomeNumber;

    @ManyToOne
    User user;
}

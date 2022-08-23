package com.spharosacademy.project.SSGBack.user.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String loginId;
    private String userName;
    private String userPwd;
    private String userPhoneNumber;
    private String userEmail;
    private String userAddress;
    private String gender;

}

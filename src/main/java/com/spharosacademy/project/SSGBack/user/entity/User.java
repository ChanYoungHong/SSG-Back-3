package com.spharosacademy.project.SSGBack.user.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Table(name = "Member")
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @Column(name = "user_email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "member_type")
    private String memberType;

    @Column(name = "gender")
    private String gender;

    @Column(name = "user_birthdate")
    private LocalDateTime userBirthDate;

    @Column(name = "user_drop_check")
    private Boolean userDropCheck;

    @Column(name = "")
    private boolean fromSocial;

    @ElementCollection(fetch = FetchType.LAZY)
    @Builder.Default
    private Set<UserRole> roleSet = new HashSet<>();

    public void addUserRole(UserRole userRole) {
        roleSet.add(userRole);
    }

//    public void changeUserPhone(String userPhone) {
//        this.userPhone = userPhone;
//    }
//
//    public void changeUserEmail(String userEmail) {
//        this.userEmail = userEmail;
//    }

}

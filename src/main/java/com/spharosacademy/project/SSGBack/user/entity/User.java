package com.spharosacademy.project.SSGBack.user.entity;

import com.spharosacademy.project.SSGBack.util.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Transactional

public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 사용자 ID
    @Column(name = "user_id")
    private String userId;

    @Column(name = "user_pwd", nullable = false)
    private String userPwd;

    @Column(name = "user_name", nullable = false, length = 100)
    private String userName;

    @Column(name = "user_address", nullable = false)
    private String userAddress;

    @Column(name = "user_Email")
    private String userEmail;

    @Column(name = "user_phone")
    private String userPhone;

    @Column(name = "member_type")
    private String memberType;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Column(name = "user_drop_check") // 회원 탈퇴 여부 확인하기.
    private Boolean userDropCheck;

    private boolean fromSocial;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {



        UserRole userRole = getRole();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole.toString());
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(authority);

        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return userId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}

package com.spharosacademy.project.SSGBack.user.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Transactional
@Builder
@Table(name = "user")
public class User extends BaseEntity implements UserDetails {

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

//    @Column(name = "gender")
//    private String gender;

    @Enumerated(EnumType.STRING)
    private UserRole role;

//    @Column(name = "user_birthdate")
//    private LocalDate userBirthDate;

    @Column(name = "user_drop_check") // 회원 탈퇴 여부 확인하기.
    private Boolean userDropCheck;

    private boolean fromSocial;

//    @ElementCollection(fetch = FetchType.EAGER)
//    @Builder.Default
//    private List<String> roles = new ArrayList<>();

//    @ElementCollection(fetch = FetchType.LAZY)
//    @Builder.Default
//    private Set<UserRole> roleSet = new HashSet<>();
//
//    public void addUserRole(UserRole userRole) {
//        roleSet.add(userRole);
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

//        return this.roles.stream()
//            .map(SimpleGrantedAuthority::new)
//            .collect(Collectors.toList());

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
        return userName;
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

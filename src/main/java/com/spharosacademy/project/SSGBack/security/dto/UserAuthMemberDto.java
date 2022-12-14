package com.spharosacademy.project.SSGBack.security.dto;

import java.util.Collection;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

@Log4j2
@Getter
@Setter
@ToString
public class UserAuthMemberDto extends User {

    private String userEmail;
    private String userName;
    private boolean fromSocial;

    public UserAuthMemberDto(String username,
                             String password,
                             boolean fromSocial,
                             Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);

        this.userName = username;
        this.fromSocial = fromSocial;
    }


}

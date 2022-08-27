package com.spharosacademy.project.SSGBack.oauth2.domain;

import com.spharosacademy.project.SSGBack.user.entity.User;
import java.io.Serializable;
import lombok.Getter;

@Getter
public class SessionUser implements Serializable {
    private String userName;
    private String userEmail;

    public SessionUser(User user) {

        this.userName = userName;
        this.userEmail = userEmail;
    }

}

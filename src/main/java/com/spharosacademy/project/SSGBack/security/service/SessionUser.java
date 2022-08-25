package com.spharosacademy.project.SSGBack.security.service;

import com.spharosacademy.project.SSGBack.user.entity.User;
import java.io.Serializable;

public class SessionUser implements Serializable {

    private String userName;
    private String userEmail;
    private String picture;

    public SessionUser(User user) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.picture = picture;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

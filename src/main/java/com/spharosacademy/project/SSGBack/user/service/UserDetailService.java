package com.spharosacademy.project.SSGBack.user.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {

    UserDetailService loadUserByUserId(String userId) throws UsernameNotFoundException;
}

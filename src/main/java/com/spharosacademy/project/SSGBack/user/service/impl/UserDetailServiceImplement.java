package com.spharosacademy.project.SSGBack.user.service.impl;

import com.spharosacademy.project.SSGBack.user.repository.IUserRepository;
import com.spharosacademy.project.SSGBack.user.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailServiceImplement implements UserDetailService {

    private final Logger logger= LoggerFactory.getLogger(UserDetailServiceImplement.class);

    private final IUserRepository iUserRepository;

    @Override
    public UserDetailService loadUserByUserId(String userId) throws UsernameNotFoundException {
        logger.info("[loadUserByUsername] loadUserByUsername 수행, username : {}", userId);
        return iUserRepository.getByUserId();
    }
}

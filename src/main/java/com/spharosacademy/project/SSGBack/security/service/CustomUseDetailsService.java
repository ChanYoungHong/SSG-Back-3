package com.spharosacademy.project.SSGBack.security.service;

import com.spharosacademy.project.SSGBack.security.dto.UserAuthMemberDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class CustomUseDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // UserDetails를 가져오기 위한 DAO다
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Optional<User> result = userRepository.findById(Long.valueOf(id));

        // 책에는 !가 없음, 근데 로직상 !가 있어야 할 것 같음.
        if (result.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }
        User user = result.get();
        return user;
    }
}

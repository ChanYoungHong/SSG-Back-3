package com.spharosacademy.project.SSGBack.security.service;

import com.spharosacademy.project.SSGBack.security.dto.UserAuthMemberDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.UserIdNotFound;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;

import java.util.Optional;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor                        // repository와 비슷함, 아이디주면 사용자 정보 가져오는 역할
public class CustomUseDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // UserDetails -> username, password, authority
    // UserDetails를 가져오기 위한 DAO다
    @Override                             // String id - 여기서 가져 올 것은 sub의 id값, 원래는 String username이라 명시되어 있음.
    public UserDetails loadUserByUsername(String id)  {

        Optional<User> result = userRepository.findByUserId(id);
//        Optional<User> result2 = userRepository.findById(Long.valueOf(id));

        System.out.println(result);

        if (result.isEmpty()) {
            throw new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
        }

//        if(result2.isPresent()) {
//            return result.get();
//        }
        return result.get();
    }
}

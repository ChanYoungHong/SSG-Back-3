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
public class UseDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("UserDetailsService loadUserByname " + username);

        Optional<User> result = userRepository.findByUserEmail(username, false);

        if(result.isPresent()){
            throw new UsernameNotFoundException("Check Email or Social");
        }

        User user = result.get();

        log.info("============================");
        log.info(user);

        UserAuthMemberDto userAuthMemberDto = new UserAuthMemberDto(
            user.getUserEmail(),
            user.getUserPwd(),
            user.isFromSocial(),
            user.getRoleSet().stream()
                .map(role -> new SimpleGrantedAuthority
                    ("ROLE_" + role.name())).collect(Collectors.toSet())
        );

        userAuthMemberDto.setUserName(user.getUserName());
        userAuthMemberDto.setFromSocial(user.isFromSocial());

        return userAuthMemberDto;
    }
}

package com.spharosacademy.project.SSGBack.security.filter;

import com.amazonaws.util.IOUtils;
import com.fasterxml.jackson.core.StreamReadFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.spharosacademy.project.SSGBack.security.dto.response.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.security.dto.response.LoginUnSuccessfulOutputDto;
import com.spharosacademy.project.SSGBack.security.service.CustomUseDetailsService;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.entity.User;
import com.spharosacademy.project.SSGBack.user.exception.NotMatchPassword;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter implements Filter {

    private CustomUseDetailsService customUseDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper mapper;


    public JwtLoginFilter(@Lazy String processUrl,
                          @Lazy CustomUseDetailsService customUseDetailsService,
                          @Lazy JwtTokenProvider jwtTokenProvider,
                          @Lazy UserRepository userRepository,
                          @Lazy PasswordEncoder passwordEncoder,
                          ObjectMapper mapper,
                          UserDetailsService userDetailsService) {

        super(new AntPathRequestMatcher(processUrl, "POST"));
        this.customUseDetailsService = customUseDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mapper = mapper;
        this.userDetailsService = userDetailsService;
    }


    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        UserLoginDto userLoginDto = mapper.readValue(request.getReader(), UserLoginDto.class);

        UserDetails userDetails = userDetailsService.loadUserByUsername(userLoginDto.getUserId());

        if (!passwordEncoder.matches(userLoginDto.getUserPwd(), userDetails.getPassword())) {
            throw new BadCredentialsException("비밀번호가 일치하지않습니다.");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails,
                null,
                userDetails.getAuthorities());
    }


    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response, FilterChain chain,
                                            Authentication authResult)
            throws IOException, ServletException {

        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        User user = (User) userDetails;

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                jwtTokenProvider.createToken(userDetails.getUsername(), String.valueOf(userDetails.getAuthorities())),
                null,
                userDetails.getAuthorities());

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        mapper.writeValue(response.getWriter(),
                LoginSuccessOutputDto.builder()
                        .message("토큰이 생성 되었습니다.")
                        .isSuccess("성공")
                        .result((String) authenticationToken.getPrincipal())
                        .userEmail(user.getUserEmail())
                        .userAddress(user.getUserAddress())
                        .name(user.getName())
                        .memberType(user.getMemberType())
                        .userPhoneNumber(user.getUserPhone())
                        .build());
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request,
                                              HttpServletResponse response,
                                              AuthenticationException failed)
            throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(400);

        mapper.writeValue(response.getWriter(),
                LoginUnSuccessfulOutputDto.builder()
                        .isSuccess("로그인을 실패했습니다.")
                        .message("아이디 혹은 비밀번호를 다시 확인해주세요.")
                        .build());
    }

}

package com.spharosacademy.project.SSGBack.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spharosacademy.project.SSGBack.security.dto.LoginSuccessOutputDto;
import com.spharosacademy.project.SSGBack.security.service.CustomUseDetailsService;
import com.spharosacademy.project.SSGBack.user.dto.request.UserLoginDto;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Slf4j
public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {
    private  CustomUseDetailsService customUseDetailsService;
    private final JwtTokenProvider jwtTokenProvider;

    public JwtLoginFilter(String processUrl, CustomUseDetailsService customUseDetailsService, JwtTokenProvider jwtTokenProvider) {
        super(new AntPathRequestMatcher(processUrl, "POST"));
        this.customUseDetailsService = customUseDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        System.out.println("JwtLoginFilter의 attemptAuthentication 들어옴");
        ObjectMapper objectMapper = new ObjectMapper();
        UserLoginDto userLoginDto = objectMapper.readValue(request.getReader(), UserLoginDto.class);

//        UserDetails userDetails = customUseDetailsService.loadUserByUsername(userLoginDto.getUserId());
//        Authentication user = jwtTokenProvider.getUser(userLoginDto.getUserId());
//        jwtTokenProvider.createToken()
//        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            log.info(userLoginDto.getUserId());
        return jwtTokenProvider.getUser(userLoginDto.getUserId());
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        System.out.println("JwtLoginFilter의 successfulAuthentication 들어옴");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

//        User user = (User) ((UserDetails) authResult.getPrincipal());
        String userId = (String) authResult.getPrincipal();
        String role = String.valueOf(authResult.getAuthorities());


        objectMapper.writeValue(response.getWriter(),
                LoginSuccessOutputDto.builder()
                        .message("토큰이 생성 되었습니다.")
                        .isSuccess("성공")
                        .result(jwtTokenProvider.createToken(userId, role))
                        .build());

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        System.out.println("JwtLoginFilter의 unsuccessfulAuthentication 들어옴");
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(400);
        objectMapper.writeValue(response.getWriter(),
                LoginSuccessOutputDto.builder()
                        .message(failed.getMessage())
                        .isSuccess("실패")
                        .build());
    }
}
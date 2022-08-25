package com.spharosacademy.project.SSGBack.security.config;

import com.spharosacademy.project.SSGBack.security.service.CustomOAuth2UserService;
import com.spharosacademy.project.SSGBack.util.JwtAuthenticationFilter;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@Log4j2
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    private CustomOAuth2UserService customOAuth2Service;

    @Bean
    @Override // 인증처리 interface다
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
//                .anyRequest().permitAll() // 모든 요청에 대해서 허용
            .antMatchers().permitAll()
            .antMatchers("/test/**").authenticated() // 인증이 필요하다고 요청 함
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/admin/**").hasRole("MANAGER")
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class)
            .oauth2Login() // oauth2
            .defaultSuccessUrl("/login-success")
            .userInfoEndpoint()
            .userService(
                customOAuth2Service); // ouath2 로그인데 성공하면, 유저 데이터를 가지고 우리가 생성한 custom ~기를 처리하


        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.httpBasic().disable();
        http.csrf().disable();
        http.logout();

    }

}

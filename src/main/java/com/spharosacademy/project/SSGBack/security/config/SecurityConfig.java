package com.spharosacademy.project.SSGBack.security.config;

import com.spharosacademy.project.SSGBack.oauth2.service.CustomOAuth2Service;
import com.spharosacademy.project.SSGBack.oauth2.service.UserOAuth2Service;
import com.spharosacademy.project.SSGBack.util.JwtAuthenticationFilter;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
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
    private final CustomOAuth2Service customOAuth2Service;
    private final UserOAuth2Service userOAuth2Service;


    @Bean
    @Override // 인증처리 interface다
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().mvcMatchers("/members/**", "/images/**");
        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.logout();
        http.httpBasic().disable().authorizeRequests()
            .antMatchers().permitAll()
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/admin/**").hasRole("MANAGER")
            .and()
            .logout()
//            .logoutSuccessUrl("/")
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class)
            .oauth2Login() // oauth2
//            .defaultSuccessUrl("/login-success")/
            .userInfoEndpoint()
            .userService(customOAuth2Service);// ouath2 로그인데 성공하면, 유저 데이터를 가지고 우리가 생성한 custom ~기를 처리하
//            .userService(userOAuth2Service);

        super.configure(http);
    }

}

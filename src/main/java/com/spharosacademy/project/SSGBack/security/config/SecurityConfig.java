package com.spharosacademy.project.SSGBack.security.config;

import com.spharosacademy.project.SSGBack.oauth2.service.CustomOAuth2Service;
import com.spharosacademy.project.SSGBack.security.exception.CustomAuthenticationEntryPoint;
import com.spharosacademy.project.SSGBack.security.filter.JwtFilter;
import com.spharosacademy.project.SSGBack.security.filter.JwtLoginFilter;
import com.spharosacademy.project.SSGBack.security.filter.JwtRequestFilter;
import com.spharosacademy.project.SSGBack.security.handler.OAuth2AuthenticationSuccessHandler;
import com.spharosacademy.project.SSGBack.security.service.CustomUseDetailsService;
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
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@Log4j2
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;
    private final CorsFilter corsFilter;
    private final CustomUseDetailsService customUseDetailsService;
    private final JwtFilter jwtFilter;

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
//        return new ProviderManager(new JwtTokenProvider());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().mvcMatchers("/members/**", "/images/**");
//        web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations());
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.logout().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.headers().frameOptions().disable();
        http.rememberMe().disable();
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
            .antMatchers("/login").permitAll()
            .antMatchers("/user/**").authenticated()
            .anyRequest().permitAll()
                .and().exceptionHandling()
            .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
//                .antMatchers("/user/**").hasRole("USER")
//                .and()
////                .logout()
//                .exceptionHandling()
////                .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
////                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                .and()
//                .oauth2Login() // oauth2
//                .authorizationEndpoint()
//                .baseUri("/oauth/authorize")
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .userInfoEndpoint()
//                .userService(customOAuth2Service);// ouath2 로그인데 성공하면, 유저 데이터를 가지고 우리가 생성한 custom ~기를 처리하
////            .userService(userOAuth2Service);

        http.addFilterBefore(corsFilter, SecurityContextPersistenceFilter.class);
        http.addFilterBefore(
            new JwtLoginFilter("/login", customUseDetailsService, jwtTokenProvider),
            UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(jwtFilter,
            UsernamePasswordAuthenticationFilter.class);

    }
}

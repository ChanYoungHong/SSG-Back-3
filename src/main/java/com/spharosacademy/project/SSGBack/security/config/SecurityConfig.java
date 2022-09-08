package com.spharosacademy.project.SSGBack.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spharosacademy.project.SSGBack.oauth2.service.CustomOAuth2Service;
import com.spharosacademy.project.SSGBack.oauth2.service.UserOAuth2Service;
import com.spharosacademy.project.SSGBack.security.exception.CustomAuthenticationEntryPoint;
import com.spharosacademy.project.SSGBack.security.filter.JwtFilter;
import com.spharosacademy.project.SSGBack.security.filter.JwtLoginFilter;
import com.spharosacademy.project.SSGBack.security.service.CustomUseDetailsService;
import com.spharosacademy.project.SSGBack.user.repo.UserRepository;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.web.filter.CorsFilter;

@SuppressWarnings("deprecation")
@EnableWebSecurity
@Configuration
@Log4j2
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public SecurityConfig(@Lazy JwtTokenProvider jwtTokenProvider,
                          CorsFilter corsFilter,
                          @Lazy CustomUseDetailsService customUseDetailsService,
                          @Lazy JwtFilter jwtFilter,
                          @Lazy UserRepository userRepository,
                          CustomOAuth2Service customOAuth2Service, @Lazy PasswordEncoder passwordEncoder, UserDetailsService userDetailsService, UserOAuth2Service userOAuth2Service) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.corsFilter = corsFilter;
        this.customUseDetailsService = customUseDetailsService;
        this.jwtFilter = jwtFilter;
        this.userRepository = userRepository;
        this.customOAuth2Service = customOAuth2Service;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.userOAuth2Service = userOAuth2Service;
    }

    private final JwtTokenProvider jwtTokenProvider;
    private final CorsFilter corsFilter;
    private final CustomUseDetailsService customUseDetailsService;
    private final JwtFilter jwtFilter;
    private final UserRepository userRepository;

    private final CustomOAuth2Service customOAuth2Service;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserOAuth2Service userOAuth2Service;
    private final ObjectMapper mapper = new ObjectMapper();

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
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().permitAll()
                .and().exceptionHandling()
                .authenticationEntryPoint(new CustomAuthenticationEntryPoint());
        http.addFilterBefore(corsFilter, SecurityContextPersistenceFilter.class);
        http.addFilterBefore(
                new JwtLoginFilter("/login", customUseDetailsService, jwtTokenProvider, userRepository,
                        passwordEncoder, mapper, userDetailsService),
                UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//                .and()
//                .oauth2Login()
//                .userInfoEndpoint() // OAuth2 로그인 성공 후 가져올 설정들
//                .userService(customOAuth2Service); // 서버에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시    }

////                .authorizationEndpoint()
//                .baseUri("/oauth/authorize")
//                .and()
//                .userInfoEndpoint();
//                .userService(customOAuth2Service);// ouath2 로그인데 성공하면, 유저 데이터를 가지고 우리가 생성한 custom ~기를 처리하
//            .userService(userOAuth2Service);


////                .accessDeniedHandler(new CustomAccessDeniedHandler())
//                .and()
//                .oauth2Login() // oauth2
//                .authorizationEndpoint()
//                .baseUri("/oauth/authorize")
//                .and()
//                .successHandler(oAuth2AuthenticationSuccessHandler)
//                .userInfoEndpoint()


    }
}

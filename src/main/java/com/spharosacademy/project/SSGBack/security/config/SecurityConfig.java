package com.spharosacademy.project.SSGBack.security.config;

import com.spharosacademy.project.SSGBack.oauth2.service.CustomOAuth2Service;
import com.spharosacademy.project.SSGBack.security.domain.JwtAuthenticationEntryPoint;
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

    private final CustomOAuth2Service customOAuth2Service;
    private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
    private final CustomUseDetailsService customUseDetailsService;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

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
<<<<<<< HEAD
        http.csrf().disable();
        http.logout().disable();
        http.httpBasic().disable();
        http.formLogin().disable();
        http.headers().frameOptions().disable();
        http.rememberMe().disable();
        http.sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/**").permitAll()
                .and().exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint);
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
=======
        http.httpBasic().disable().authorizeRequests()
            .antMatchers().permitAll()
            .antMatchers("/user/**").hasRole("USER")
            .antMatchers("/admin/**").hasRole("MANAGER")
            .and()
            .logout()
            .logoutSuccessUrl("/")
            .and()
            .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                UsernamePasswordAuthenticationFilter.class)
            .oauth2Login() // oauth
            .authorizationEndpoint()
            .baseUri("/oauth2.0/authorize")
            .and()
            .successHandler(oAuth2AuthenticationSuccessHandler)
//            .defaultSuccessUrl("/login-success")
            .userInfoEndpoint()
            .userService(customOAuth2Service);// ouath2 로그인데 성공하면, 유저 데이터를 가지고 우리가 생성한 custom ~기를 처리하
//            .userService(userOAuth2Service);
>>>>>>> f053093 (소셜 로그인 설정 및 수정)

//        super.configure(http);
        http.addFilterBefore(corsFilter, SecurityContextPersistenceFilter.class);
        http.addFilterBefore(new JwtLoginFilter("/login", customUseDetailsService, jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(new JwtRequestFilter("/user"), UsernamePasswordAuthenticationFilter.class);

    }
}

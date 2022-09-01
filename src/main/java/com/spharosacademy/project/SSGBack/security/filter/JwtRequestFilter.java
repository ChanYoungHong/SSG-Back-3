package com.spharosacademy.project.SSGBack.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spharosacademy.project.SSGBack.security.domain.Code;
import com.spharosacademy.project.SSGBack.util.JwtTokenProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j

public class JwtRequestFilter extends AbstractAuthenticationProcessingFilter {
    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

    public JwtRequestFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        // 토큰을 검증하는 로직

//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        context.setAuthentication();
//        SecurityContextHolder.setContext(context);

//        log.info(header);
//        log.info("RequestFilter에 요청이 들어오는지");
//        if (header != null && header.startsWith(TOKEN_PREFIX)) {
//            log.info("if문 안으로는 들어 왔는지 !!!!!!!");
//            authToken = header.replace(TOKEN_PREFIX, "");
//            log.info("if문 안으로는 들어 왔는지???");
//            try {
//                log.info("try catch 문에 들어가는지 ?!?!!?");
//                username = jwtTokenProvider.getUserPk(authToken);
//            } catch (IllegalArgumentException e) {
//                // 예외일 경우 처리로직
//                throw new JwtException("유효하지 않은 토큰");
//            } catch (ExpiredJwtException e) {
//                throw new JwtException("토큰 기한 만료");
//            }
//            chain.doFilter(req, res);
//        }

    }



    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }

}

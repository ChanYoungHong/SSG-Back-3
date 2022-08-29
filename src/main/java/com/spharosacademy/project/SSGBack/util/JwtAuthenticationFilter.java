package com.spharosacademy.project.SSGBack.util;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

@RequiredArgsConstructor
@Slf4j
@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private JwtTokenProvider jwtTokenProvider;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailsService;


    String HEADER_STRING = "Authorization";
    String TOKEN_PREFIX = "Bearer ";

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {

        // 헤더에서 Jwt를 받아온다.
        String token = jwtTokenProvider.resolveToken((HttpServletRequest) servletRequest);


        // 유효한 토큰인지 확인
        if( token != null && jwtTokenProvider.validateToken(token)) {
            // 토큰이 유효하면 토큰으로부터 유저 정보를 받아옵니다.
            Authentication authentication = jwtTokenProvider.getAuthenication(token);
            // SecurityContext에 Authentication 객체를 저장한다.
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
//                                    FilterChain filterChain) throws IOException, ServletException {
//        String header = request.getHeader(HEADER_STRING);
//        String username = null;
//        String authToken = null;
//
//        if(header != null && header.startsWith(TOKEN_PREFIX)){
//            authToken = header.replace(TOKEN_PREFIX, "");
//
//            try {
//                username = jwtTokenProvider.getUserPk(authToken);
//            }catch (IllegalArgumentException e) {
//                logger.error("an error occured during getting username from token", e);
//                // JwtException (custom exception) 예외 발생시키기
//                throw new JwtException("유효하지 않은 토큰");
//            } catch (ExpiredJwtException e) {
//                logger.warn("the token is expired and not valid anymore", e);
//                throw new JwtException("토큰 기한 만료");
//            } catch(SignatureException e){
//                logger.error("Authentication Failed. Username or Password not valid.");
//                throw new JwtException("사용자 인증 실패");
//            }
//        } else {
//            logger.warn("couldn't find bearer string, will ignore the header");
//        }
//
//    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("unsuccessfulAuthentication failed.getLocalizedMessage(): {}", failed.getLocalizedMessage());

        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("code", HttpStatus.UNAUTHORIZED.value());
        body.put("error", failed.getMessage());

        new ObjectMapper().writeValue(response.getOutputStream(), body);
    }
}
